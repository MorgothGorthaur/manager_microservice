package executor.service.dao.repository

import com.redis.om.spring.repository.RedisDocumentRepository
import executor.service.model.Scenario

interface ScenarioRepository : RedisDocumentRepository<Scenario, String> {
    fun searchByName(name: String): List<Scenario>
    fun searchBySite(site: String): List<Scenario>
}