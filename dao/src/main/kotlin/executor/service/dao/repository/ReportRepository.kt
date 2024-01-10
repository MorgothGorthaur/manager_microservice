package executor.service.dao.repository

import com.redis.om.spring.repository.RedisDocumentRepository
import executor.service.model.ScenarioReport

interface ReportRepository : RedisDocumentRepository<ScenarioReport, String> {
    fun findByScenarioId(id: String): List<ScenarioReport>
    fun deleteByScenarioId(id: String)
    fun searchByName(name: String): List<ScenarioReport>
    fun searchBySite(site: String): List<ScenarioReport>

}