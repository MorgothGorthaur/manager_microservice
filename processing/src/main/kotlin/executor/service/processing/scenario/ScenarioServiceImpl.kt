package executor.service.processing.scenario

import executor.service.dao.repository.ReportRepository
import executor.service.dao.repository.ScenarioRepository
import executor.service.model.Scenario
import executor.service.processing.paging.Paginator
import executor.service.processing.query.QueryProcessor
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
internal class ScenarioServiceImpl(
    private val scenarioRepository: ScenarioRepository,
    private val reportRepository: ReportRepository,
    private val processor: QueryProcessor,
    private val paginator: Paginator
) : ScenarioService {
    override fun add(scenario: Scenario) {
        scenarioRepository.save(scenario)
    }

    override fun update(scenario: Scenario) {
        scenarioRepository.update(scenario)
    }

    override fun delete(scenario: Scenario) {
        scenarioRepository.delete(scenario)
        reportRepository.deleteByScenarioId(scenario.id!!)
    }

    override fun findAll(pageNum: Int, pageSize: Int): Page<Scenario> {
        val data = scenarioRepository.findAll()
        return paginator.paginate(data, pageNum, pageSize)
    }

    override fun findByName(name: String, pageNum: Int, pageSize: Int): Page<Scenario> {
        val searchPattern = processor.createPattern(name)
        val data = scenarioRepository.searchByName(searchPattern)
        return paginator.paginate(data, pageNum, pageSize)
    }

    override fun findBySite(site: String, pageNum: Int, pageSize: Int): Page<Scenario> {
        val searchPattern = processor.createPattern(site)
        val data = scenarioRepository.searchBySite(searchPattern)
        return paginator.paginate(data, pageNum, pageSize)
    }
}