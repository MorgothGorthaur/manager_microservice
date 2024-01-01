package executor.service.processing.report

import executor.service.dao.repository.ReportRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ReportProcessingServiceImpl(private val repo: ReportRepository) : ReportProcessingService {
    override fun findAll(request: Pageable) = repo.findAll(request)
    override fun findByScenarioId(scenarioId: String, request: Pageable) = repo.findPageByScenarioId(scenarioId, request)
    override fun findByName(name: String, request: Pageable) = repo.searchByName(name, request)
    override fun findBySite(site: String, request: Pageable) = repo.searchBySite(site, request)
}