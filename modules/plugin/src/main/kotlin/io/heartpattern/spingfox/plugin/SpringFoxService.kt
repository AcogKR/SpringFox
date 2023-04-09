package io.heartpattern.spingfox.plugin

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.stereotype.Service

@Service
class SpringFoxService {

    @PostConstruct
    fun start() { // plugin start

    }

    @PreDestroy
    fun stop() { // plugin stop

    }
}