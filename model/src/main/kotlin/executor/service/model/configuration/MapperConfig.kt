package executor.service.model.configuration

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class MapperConfig {
    @Bean
    fun mapper() = jacksonObjectMapper().apply { registerModule(JavaTimeModule()) }
}