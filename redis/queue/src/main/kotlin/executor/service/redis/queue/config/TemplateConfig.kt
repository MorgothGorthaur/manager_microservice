package executor.service.redis.queue.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate

@Configuration
class TemplateConfig(private val jedisConnectionFactory: JedisConnectionFactory) {
    @Bean
    fun redisTemplate() = StringRedisTemplate().apply { connectionFactory = jedisConnectionFactory }
}