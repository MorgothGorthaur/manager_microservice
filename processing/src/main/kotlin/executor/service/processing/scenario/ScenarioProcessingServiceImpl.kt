package executor.service.processing.scenario

import executor.service.model.Scenario
import executor.service.processing.model.PageConfig
import executor.service.dao.repository.ScenarioRepository

import org.springframework.stereotype.Service

@Service
class ScenarioProcessingServiceImpl(private val repo: ScenarioRepository) : ScenarioProcessingService {
    override fun add(scenario: Scenario) { repo.save(scenario) }

    override fun update(scenario: Scenario) { repo.update(scenario) }

    override fun delete(scenario: Scenario) { repo.delete(scenario) }

    override fun findAll(pageConfig: PageConfig) = repo.findAll(pageConfig.request())

    override fun findByName(name: String, pageConfig: PageConfig) =
        repo.findPageByNameContaining(name, pageConfig.request())

    override fun findBySite(site: String, pageConfig: PageConfig) = repo.findPageBySiteContaining(site, pageConfig.request())
}