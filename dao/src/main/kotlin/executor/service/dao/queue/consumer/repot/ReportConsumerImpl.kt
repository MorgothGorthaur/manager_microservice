package executor.service.dao.queue.consumer.repot

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import executor.service.model.ScenarioReport
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
internal class ReportConsumerImpl(
    private val template: StringRedisTemplate,
    private val mapper: ObjectMapper
) : ReportConsumer {
    private val key = "report.queue.key"
    override fun poll() = template.opsForList().rightPop(key)
        ?.let{ mapper.readValue<ScenarioReport>(it) }

}