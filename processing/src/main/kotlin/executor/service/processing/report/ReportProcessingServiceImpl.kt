package executor.service.processing.report

import executor.service.dao.repository.ReportRepository
import executor.service.model.ScenarioReport
import executor.service.processing.paging.Paginator
import executor.service.processing.query.QueryProcessor
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class ReportProcessingServiceImpl(
    private val repo: ReportRepository,
    private val processor: QueryProcessor,
    private val paginator: Paginator
) : ReportProcessingService {
    override fun findAll(pageNum: Int, pageSize: Int): Page<ScenarioReport> {
        val data = repo.findAll().sortedBy { it.startTime }
        return paginator.paginate(data, pageNum, pageSize)
    }
    override fun findByScenarioId(scenarioId: String, pageNum: Int, pageSize: Int) : Page<ScenarioReport> {
        val data = repo.findByScenarioId(scenarioId).sortedBy { it.startTime }
        return paginator.paginate(data, pageNum, pageSize)
    }

    override fun findByName(name: String, pageNum: Int, pageSize: Int) : Page<ScenarioReport> {
        val searchPattern = processor.createPattern(name)
        val data = repo.searchByName(searchPattern).sortedBy { it.startTime }
        return paginator.paginate(data, pageNum, pageSize)
    }

    override fun findBySite(site: String, pageNum: Int, pageSize: Int): Page<ScenarioReport> {
        val searchPattern = processor.createPattern(site)
        val data = repo.searchBySite(searchPattern).sortedBy { it.startTime }
        return paginator.paginate(data, pageNum, pageSize )
    }
}