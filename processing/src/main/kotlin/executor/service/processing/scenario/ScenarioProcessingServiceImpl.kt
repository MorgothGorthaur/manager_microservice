package executor.service.processing.scenario

import executor.service.dao.repository.ScenarioRepository
import executor.service.model.Scenario
import executor.service.processing.paging.Paginator
import executor.service.processing.query.QueryProcessor
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class ScenarioProcessingServiceImpl(
    private val repo: ScenarioRepository,
    private val processor: QueryProcessor,
    private val paginator: Paginator
) : ScenarioProcessingService {
    override fun add(scenario: Scenario) {
        repo.save(scenario)
    }

    override fun update(scenario: Scenario) {
        repo.update(scenario)
    }

    override fun delete(scenario: Scenario) {
        repo.delete(scenario)
    }

    override fun findAll(pageNum: Int, pageSize: Int): Page<Scenario> {
        val data = repo.findAll()
        return paginator.paginate(data, pageNum, pageSize)
    }

    override fun findByName(name: String, pageNum: Int, pageSize: Int): Page<Scenario> {
        val searchPattern = processor.createPattern(name)
        val data = repo.searchByName(searchPattern)
        return paginator.paginate(data, pageNum, pageSize)
    }

    override fun findBySite(site: String, pageNum: Int, pageSize: Int): Page<Scenario> {
        val searchPattern = processor.createPattern(site)
        val data = repo.searchBySite(searchPattern)
        return paginator.paginate(data, pageNum, pageSize)
    }
}