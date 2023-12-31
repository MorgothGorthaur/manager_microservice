package executor.service.dao.repository

import com.redis.om.spring.repository.RedisDocumentRepository
import executor.service.model.ScenarioReport
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ReportRepository : RedisDocumentRepository<ScenarioReport, String> {
    fun findPageByScenarioId(id: String, pageable: Pageable): Page<ScenarioReport>
    fun searchByName(name: String, pageable: Pageable): Page<ScenarioReport>
    fun searchBySite(site: String, pageable: Pageable): Page<ScenarioReport>

}