package executor.service.processing.report

import executor.service.model.ScenarioReport
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ReportProcessingService {
    fun findAll(request: Pageable): Page<ScenarioReport>
    fun findByScenarioId(scenarioId: String, request: Pageable): Page<ScenarioReport>
    fun findByName(name: String, request: Pageable): Page<ScenarioReport>
    fun findBySite(site: String, request: Pageable): Page<ScenarioReport>
}