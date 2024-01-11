package executor.service.processing.report

import executor.service.model.ScenarioReport
import org.springframework.data.domain.Page

interface ReportService {
    fun findAll(pageNum: Int, pageSize: Int): Page<ScenarioReport>
    fun findByScenarioId(scenarioId: String, pageNum: Int, pageSize: Int): Page<ScenarioReport>
    fun findByName(name: String, pageNum: Int, pageSize: Int): Page<ScenarioReport>
    fun findBySite(site: String, pageNum: Int, pageSize: Int): Page<ScenarioReport>
}