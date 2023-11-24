package executor.service.redis.queue.listener.repot

import com.fasterxml.jackson.databind.ObjectMapper
import executor.service.model.ScenarioReport
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component


@Component
class ReportQueueListenerImpl(
    private val template: StringRedisTemplate,
    @Qualifier("queue_mapper")
    private val mapper: ObjectMapper
) : ReportQueueListener {
    private val key = "report.queue.key"
    override fun poll() = template.opsForList().rightPop(key)
        ?.let{ mapper.readValue(it, ScenarioReport::class.java) }

}