package executor.service.dao.queue.listener.repot

import executor.service.model.ScenarioReport
import executor.service.dao.queue.listener.QueueListener

interface ReportQueueListener : QueueListener<ScenarioReport> {
}