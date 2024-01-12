package executor.service.dao.queue.consumer.repot

import executor.service.model.ScenarioReport
import executor.service.dao.queue.consumer.QueueConsumer

interface ReportConsumer : QueueConsumer<ScenarioReport> {
}