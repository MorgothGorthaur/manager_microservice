package executor.service

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@EnableRedisDocumentRepositories
class ManagerApplication
fun main(args: Array<String>) { runApplication<ManagerApplication>(*args) }
