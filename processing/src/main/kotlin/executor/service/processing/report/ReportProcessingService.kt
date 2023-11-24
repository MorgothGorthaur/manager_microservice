package executor.service.processing.report

import executor.service.model.ScenarioReport
import executor.service.processing.model.PageConfig
import org.springframework.data.domain.Page

interface ReportProcessingService {

    fun findByScenarioId(scenarioId: String, pageConfig: PageConfig): Page<ScenarioReport>

    fun findSuccessful(scenarioId: String, pageConfig: PageConfig): Page<ScenarioReport>

    fun findFailed(scenarioId: String, pageConfig: PageConfig): Page<ScenarioReport>
}