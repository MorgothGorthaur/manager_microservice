package executor.service.dao.config

import executor.service.dao.model.RedisConfigHolder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate

@Configuration
internal class RedisConfig(private val holder: RedisConfigHolder) {
    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        val config = RedisStandaloneConfiguration(holder.redisHost, holder.redisPort)
        val jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build()
        return JedisConnectionFactory(config, jedisClientConfiguration).apply { afterPropertiesSet() }
    }

    @Bean
    fun redisTemplate() = StringRedisTemplate().apply { connectionFactory = jedisConnectionFactory() }
}