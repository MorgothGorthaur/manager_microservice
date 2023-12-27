package executor.service.processing.report

import executor.service.model.ScenarioReport
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ReportProcessingService {
    fun findByScenarioId(scenarioId: String, request: Pageable): Page<ScenarioReport>
    fun findSuccessfulByName(name: String, request: Pageable): Page<ScenarioReport>
    fun findFailedByName(name: String, request: Pageable): Page<ScenarioReport>
    fun findSuccessfulBySite(site: String, request: Pageable): Page<ScenarioReport>
    fun findFailedBySite(site: String, request: Pageable): Page<ScenarioReport>
}