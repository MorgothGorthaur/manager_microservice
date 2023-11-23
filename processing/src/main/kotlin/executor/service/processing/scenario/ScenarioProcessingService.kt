package executor.service.processing.scenario

import executor.service.model.Scenario
import org.springframework.data.domain.Page

interface ScenarioProcessingService {
    fun add(scenario: Scenario)

    fun findAll(pageNum: Int, pageSize: Int): Page<Scenario>

    fun findByName(pageNum: Int, pageSize: Int, name: String): Page<Scenario>

    fun findBySite(pageNum: Int, pageSize: Int, site: String): Page<Scenario>

}