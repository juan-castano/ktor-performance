package com.example

import redis.clients.jedis.DefaultJedisClientConfig
import redis.clients.jedis.HostAndPort
import redis.clients.jedis.JedisPooled
import redis.clients.jedis.UnifiedJedis

open class RedisConnection() {

    private val connection: UnifiedJedis

    init {
        val endpoint = HostAndPort("127.0.0.1", 6379)
        val configuration = DefaultJedisClientConfig
            .builder()
            .ssl(false)
            .build()
        // connection = JedisPooled(endpoint,configuration)
        connection = UnifiedJedis(endpoint,configuration)
    }

    fun getConnection(): UnifiedJedis {
        return connection
    }

    companion object {
        @Volatile private var instance: RedisConnection? = null

        fun getInstance(): RedisConnection {
            return instance ?: synchronized(this) {
                instance ?: RedisConnection().also { instance = it }
            }
        }
    }
}