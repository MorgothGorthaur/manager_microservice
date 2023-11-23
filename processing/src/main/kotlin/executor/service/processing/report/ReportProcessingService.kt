package executor.service.processing.report

import executor.service.model.ScenarioReport
import org.springframework.data.domain.Page

interface ReportProcessingService {
    fun findLastByScenarioId(size: Int, scenarioId: String): List<ScenarioReport>

    fun findByScenarioId(pageNum: Int, pageSize: Int, scenarioId: String): Page<ScenarioReport>

    fun findSuccessful(pageNum: Int, pageSize: Int, scenarioId: String): Page<ScenarioReport>

    fun findFailed(pageNum: Int, pageSize: Int, scenarioId: String): Page<ScenarioReport>
}