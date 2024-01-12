package executor.service.processing.report

import executor.service.dao.queue.consumer.repot.ReportConsumer
import executor.service.dao.repository.ReportRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
internal class ScheduledReportListener(
    private val listener: ReportConsumer,
    private val repo: ReportRepository
) : ReportListener {

    @Scheduled(fixedDelay = 120000)
    override fun execute() { generateSequence { listener.poll() }.forEach { repo.save(it) } }
}