package io.heartpattern.spingfox.plugin

import io.heartpattern.springfox.paper.command.annotation.CommandHandler
import io.heartpattern.springfox.paper.command.model.CommandInvocation
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import org.springframework.stereotype.Component

@Component
class SpringFoxContents {

    @CommandHandler(
        name = ["test"]
    )
    fun command(invocation: CommandInvocation) {

    }


    @EventHandler
    fun event(event: PlayerJoinEvent) {

    }
}