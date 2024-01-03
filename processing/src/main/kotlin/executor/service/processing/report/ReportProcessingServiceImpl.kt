package executor.service.processing.report

import executor.service.dao.repository.ReportRepository
import executor.service.processing.query.QueryProcessor
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ReportProcessingServiceImpl(
    private val repo: ReportRepository,
    private val processor: QueryProcessor
) : ReportProcessingService {
    override fun findAll(request: Pageable) = repo.findAll(request)
    override fun findByScenarioId(scenarioId: String, request: Pageable) =
        repo.findPageByScenarioId(scenarioId, request)

    override fun findByName(name: String, request: Pageable) =
        processor.createPattern(name).run { repo.searchByName(this, request) }

    override fun findBySite(site: String, request: Pageable) =
        processor.createPattern(site).run { repo.searchBySite(site, request) }
}