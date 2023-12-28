package executor.service.dao.repository

import com.redis.om.spring.repository.RedisDocumentRepository
import executor.service.model.Scenario
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface ScenarioRepository : RedisDocumentRepository<Scenario, String>, PagingAndSortingRepository<Scenario, String> {
    fun searchByName(name: String, pageable: Pageable): Page<Scenario>
    fun searchBySite(site: String, pageable: Pageable): Page<Scenario>
}