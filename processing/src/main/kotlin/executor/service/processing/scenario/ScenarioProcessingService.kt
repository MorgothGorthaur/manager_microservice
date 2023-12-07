package executor.service.processing.scenario

import executor.service.model.Scenario
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface ScenarioProcessingService {
    fun add(scenario: Scenario)

    fun update(scenario: Scenario)

    fun delete(scenario: Scenario)

    fun findAll(request: PageRequest): Page<Scenario>

    fun findByName(name: String, request: PageRequest): Page<Scenario>

    fun findBySite(site: String, request: PageRequest): Page<Scenario>

}