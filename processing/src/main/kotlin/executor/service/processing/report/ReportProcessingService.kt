package executor.service.processing.report

import executor.service.model.ScenarioReport
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ReportProcessingService {
    fun findAll(pageNum: Int, pageSize: Int): Page<ScenarioReport>
    fun findByScenarioId(scenarioId: String, pageNum: Int, pageSize: Int): Page<ScenarioReport>
    fun findByName(name: String, pageNum: Int, pageSize: Int): Page<ScenarioReport>
    fun findBySite(site: String, pageNum: Int, pageSize: Int): Page<ScenarioReport>
}