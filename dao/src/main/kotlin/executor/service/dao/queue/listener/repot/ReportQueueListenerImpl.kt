package executor.service.dao.queue.listener.repot

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import executor.service.model.ScenarioReport
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
class ReportQueueListenerImpl(
    private val template: StringRedisTemplate,
    private val mapper: ObjectMapper
) : ReportQueueListener {
    private val key = "report.queue.key"
    override fun poll() = template.opsForList().rightPop(key)
        ?.let{ mapper.readValue<ScenarioReport>(it) }

}