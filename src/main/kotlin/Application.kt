package com.example

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

suspend fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080) {
        module()
    }
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}
