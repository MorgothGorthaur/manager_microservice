package executor.service.processing.report

import executor.service.dao.repository.ReportRepository
import executor.service.model.ScenarioReport
import executor.service.processing.query.QueryProcessor
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ReportProcessingServiceImpl(
    private val repo: ReportRepository,
    private val processor: QueryProcessor
) : ReportProcessingService {
    override fun findAll(request: Pageable) =
        repo.findAll().run { pageAndSort(this, request) }

    override fun findByScenarioId(scenarioId: String, request: Pageable) =
        repo.findByScenarioId(scenarioId).run { pageAndSort(this, request) }

    override fun findByName(name: String, request: Pageable) =
        processor.createPattern(name).run { repo.searchByName(this) }.run { pageAndSort(this, request) }

    override fun findBySite(site: String, request: Pageable) =
        processor.createPattern(site).run { repo.searchBySite(this) }.run { pageAndSort(this, request) }

    private fun pageAndSort(list: List<ScenarioReport>, request: Pageable): Page<ScenarioReport> {
        val sorted = list.sortedBy { it.startTime }
        val total = sorted.size
        val start = request.offset.toInt()
        val end = (start + request.pageSize).coerceAtMost(total)
        val pageList = list.subList(start, end)
        return PageImpl(pageList, request, total.toLong())
    }
}