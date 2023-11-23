package executor.service.processing.scenario

import executor.service.redis.queue.listener.repot.ReportQueueListener
import executor.service.redis.repository.ReportRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class QueueProcessingFacadeImpl(
    private val listener: ReportQueueListener,
    private val repo: ReportRepository
) : QueueProcessingFacade {

    @Scheduled(fixedDelay = 120000)
    override fun execute() { repo.saveAll(getReports()) }

    private fun getReports() = generateSequence { listener.poll() }.toList()
}