package executor.service.dao.repository

import com.redis.om.spring.repository.RedisDocumentRepository
import executor.service.model.ScenarioReport
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface ReportRepository : RedisDocumentRepository<ScenarioReport, String>, PagingAndSortingRepository<ScenarioReport, String> {
    fun findPageByScenarioId(id: String, pageable: Pageable): Page<ScenarioReport>
    fun findPageByScenarioIdAndErrorMessageIsNull(scenarioId: String, pageable: Pageable): Page<ScenarioReport>
    fun findPageByScenarioIdAndErrorMessageIsNotNull(scenarioId: String, pageable: Pageable): Page<ScenarioReport>

}