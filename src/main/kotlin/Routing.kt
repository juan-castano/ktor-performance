package com.example

import com.fasterxml.jackson.databind.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.async
import redis.clients.jedis.JedisPooled
import redis.clients.jedis.UnifiedJedis

fun Application.configureRouting() {
    val connection = RedisConnection.getInstance().getConnection()
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/redis") {
            val redisData = retrieveData(connection)
            call.respondText(redisData)
        }
    }
}

fun retrieveData(jedis: UnifiedJedis): String {
    val data = jedis.get("persistantexample")
    return data
}
