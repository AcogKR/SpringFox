package io.heartpattern.springfox.paper.core

import io.heartpattern.springfox.paper.core.coroutine.CoroutinePlugin
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.PluginClassLoader
import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.io.DefaultResourceLoader
import java.util.logging.Level
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass

/**
 * SpringFox plugin entrypoint.
 */
abstract class SpringFoxPlugin(private val applicationClass: KClass<*>) : JavaPlugin(), CoroutineScope {
    private val libraryLoaderField = PluginClassLoader::class.java.getDeclaredField("libraryLoader")
    private lateinit var applicationContext: ConfigurableApplicationContext

    final override fun onEnable() {
        val compositeClassLoader = getCompositeLoader()

        withContextClassLoader(compositeClassLoader) {
            // Fix logger issue
            System.setProperty(
                "org.springframework.boot.logging.LoggingSystem",
                "org.springframework.boot.logging.java.JavaLoggingSystem"
            )

            applicationContext = SpringApplication(applicationClass.java).apply {
                resourceLoader = DefaultResourceLoader(compositeClassLoader)
            }.run()
        }
    }

    final override fun onDisable() {
        applicationContext.close()
    }

    override val coroutineContext: CoroutineContext = SupervisorJob() +
        Dispatchers.Main +
        CoroutinePlugin(this) +
        CoroutineExceptionHandler { ctx, throwable ->
            logger.log(Level.SEVERE, throwable) {
                "Uncatched exception from coroutine $ctx"
            }
        }

    private fun getCompositeLoader(): ClassLoader {
        return CompositeClassLoader(listOf(classLoader, getLibraryLoader()))
    }

    private fun getLibraryLoader(): ClassLoader {
        libraryLoaderField.isAccessible = true

        return libraryLoaderField.get(classLoader) as ClassLoader
    }

    private inline fun withContextClassLoader(classLoader: ClassLoader, block: () -> Unit) {
        val currentClassLoader = Thread.currentThread().contextClassLoader
        Thread.currentThread().contextClassLoader = classLoader
        block()
        Thread.currentThread().contextClassLoader = currentClassLoader
    }
}