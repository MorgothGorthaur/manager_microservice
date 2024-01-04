package executor.service.processing.scenario

import executor.service.dao.repository.ScenarioRepository
import executor.service.model.Scenario
import executor.service.processing.query.QueryProcessor
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ScenarioProcessingServiceImpl(
    private val repo: ScenarioRepository,
    private val processor: QueryProcessor
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

    override fun findAll(request: PageRequest) = repo.findAll(request)

    override fun findByName(name: String, request: PageRequest) =
        processor.createPattern(name).run { repo.searchByName(this, request) }

    override fun findBySite(site: String, request: PageRequest) =
        processor.createPattern(site).run { repo.searchBySite(this, request) }
}