package executor.service.processing.scenario

import executor.service.model.Scenario
import org.springframework.data.domain.Page

interface ScenarioProcessingService {
    fun add(scenario: Scenario)

    fun update(scenario: Scenario)

    fun delete(scenario: Scenario)

    fun findAll(pageNum: Int, pageSize: Int): Page<Scenario>

    fun findByName(name: String, pageNum: Int, pageSize: Int): Page<Scenario>

    fun findBySite(site: String, pageNum: Int, pageSize: Int): Page<Scenario>

}