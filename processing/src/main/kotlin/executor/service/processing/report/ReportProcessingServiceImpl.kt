package executor.service.processing.report

import executor.service.processing.model.PageConfig
import executor.service.dao.repository.ReportRepository
import org.springframework.stereotype.Service

@Service
class ReportProcessingServiceImpl(private val repo: ReportRepository) : ReportProcessingService {

    override fun findByScenarioId(scenarioId: String, pageConfig: PageConfig) =
        repo.findPageByScenarioId(scenarioId, pageConfig.request())

    override fun findSuccessful(scenarioId: String, pageConfig: PageConfig) =
        repo.findPageByScenarioIdAndErrorMessageIsNull(scenarioId, pageConfig.request())

    override fun findFailed(scenarioId: String, pageConfig: PageConfig) =
        repo.findPageByScenarioIdAndErrorMessageIsNotNull(scenarioId, pageConfig.request())
}