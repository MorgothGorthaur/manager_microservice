package executor.service.processing.report

import executor.service.redis.repository.ReportRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ReportProcessingServiceImpl(private val repo: ReportRepository) : ReportProcessingService {
    override fun findLastByScenarioId(size: Int, scenarioId: String) = repo.run {
        val request = PageRequest.of(0, size)
        findPageByScenarioId(scenarioId, request)
    }.content.toList()

    override fun findByScenarioId(pageNum: Int, pageSize: Int, scenarioId: String) = repo.run {
        val request = PageRequest.of(pageNum, pageSize)
        findPageByScenarioId(scenarioId, request)
    }

    override fun findSuccessful(pageNum: Int, pageSize: Int, scenarioId: String) = repo.run {
        val request = PageRequest.of(pageNum, pageSize)
        findPageByScenarioIdAndErrorMessageIsNull(scenarioId, request)
    }

    override fun findFailed(pageNum: Int, pageSize: Int, scenarioId: String) = repo.run {
        val request = PageRequest.of(pageNum, pageSize)
        findPageByScenarioIdAndErrorMessageIsNotNull(scenarioId, request)
    }
}