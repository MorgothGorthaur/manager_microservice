package executor.service.processing.report

import executor.service.dao.repository.ReportRepository
import executor.service.model.ScenarioReport
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ReportProcessingServiceImpl(private val repo: ReportRepository) : ReportProcessingService {
    override fun findByScenarioId(scenarioId: String, request: Pageable) =
        repo.findPageByScenarioId(scenarioId, request)
    override fun findSuccessfulByName(name: String, request: Pageable): Page<ScenarioReport> =
        repo.searchByNameAndErrorMessageIsNull(name, request)
    override fun findFailedByName(name: String, request: Pageable): Page<ScenarioReport> =
        repo.searchByNameAndErrorMessageIsNotNull(name, request)
    override fun findSuccessfulBySite(site: String, request: Pageable): Page<ScenarioReport> =
        repo.searchBySiteAndErrorMessageIsNull(site, request)
    override fun findFailedBySite(site: String, request: Pageable): Page<ScenarioReport> =
        repo.searchBySiteAndErrorMessageIsNotNull(site, request)
}