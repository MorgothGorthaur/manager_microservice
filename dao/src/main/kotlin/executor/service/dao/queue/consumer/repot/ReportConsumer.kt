package executor.service.dao.queue.consumer.repot

import executor.service.model.ScenarioReport
import executor.service.dao.queue.consumer.QueueListener

interface ReportConsumer : QueueListener<ScenarioReport> {
}