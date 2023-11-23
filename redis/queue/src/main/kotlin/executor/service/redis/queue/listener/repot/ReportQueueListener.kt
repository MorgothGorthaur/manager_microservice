package executor.service.redis.queue.listener.repot

import executor.service.model.ScenarioReport
import executor.service.redis.queue.listener.QueueListener

interface ReportQueueListener : QueueListener<ScenarioReport> {
}