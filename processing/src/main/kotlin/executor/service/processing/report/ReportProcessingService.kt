package executor.service.processing.report

import executor.service.model.ScenarioReport
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface ReportProcessingService {

    fun findByScenarioId(scenarioId: String, request: PageRequest): Page<ScenarioReport>

    fun findSuccessful(scenarioId: String, request: PageRequest): Page<ScenarioReport>

    fun findFailed(scenarioId: String, request: PageRequest): Page<ScenarioReport>
}