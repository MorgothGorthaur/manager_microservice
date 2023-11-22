package executor.service.redis.repository

import com.redis.om.spring.repository.RedisDocumentRepository
import executor.service.model.Scenario

interface ScenarioRepository : RedisDocumentRepository<Scenario, String> {
    fun findByNameContaining(name: String): List<Scenario>
    fun findByNameContainingAndSiteContaining(name: String, site: String): List<Scenario>
}