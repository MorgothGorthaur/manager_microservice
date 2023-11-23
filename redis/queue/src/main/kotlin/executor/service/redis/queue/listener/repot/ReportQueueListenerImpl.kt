package executor.service.redis.queue.listener.repot

import executor.service.model.ScenarioReport
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component


@Component
class ReportQueueListenerImpl(private val template: RedisTemplate<String, Any>) : ReportQueueListener {
    private val key = "report.queue.key"
    override fun poll() = template.opsForList().rightPop(key) as? ScenarioReport
}