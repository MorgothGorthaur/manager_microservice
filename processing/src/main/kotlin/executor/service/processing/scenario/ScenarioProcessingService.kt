package executor.service.processing.scenario

import executor.service.model.Scenario
import executor.service.processing.model.PageConfig
import org.springframework.data.domain.Page

interface ScenarioProcessingService {
    fun add(scenario: Scenario)

    fun update(scenario: Scenario)

    fun delete(scenario: Scenario)

    fun findAll(pageConfig: PageConfig): Page<Scenario>

    fun findByName(name: String, pageConfig: PageConfig): Page<Scenario>

    fun findBySite(site: String, pageConfig: PageConfig): Page<Scenario>

}