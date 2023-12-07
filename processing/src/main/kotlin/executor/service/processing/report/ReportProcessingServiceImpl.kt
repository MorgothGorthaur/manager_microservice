package executor.service.processing.report

import executor.service.dao.repository.ReportRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ReportProcessingServiceImpl(private val repo: ReportRepository) : ReportProcessingService {

    override fun findByScenarioId(scenarioId: String, request: PageRequest) =
        repo.findPageByScenarioId(scenarioId, request)

    override fun findSuccessful(scenarioId: String, request: PageRequest) =
        repo.findPageByScenarioIdAndErrorMessageIsNull(scenarioId, request)

    override fun findFailed(scenarioId: String, request: PageRequest) =
        repo.findPageByScenarioIdAndErrorMessageIsNotNull(scenarioId, request)
}