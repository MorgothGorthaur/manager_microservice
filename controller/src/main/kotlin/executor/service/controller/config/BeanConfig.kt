package executor.service.controller.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class BeanConfig {
    @Bean
    @Primary
    fun mapper() = jacksonObjectMapper()


}