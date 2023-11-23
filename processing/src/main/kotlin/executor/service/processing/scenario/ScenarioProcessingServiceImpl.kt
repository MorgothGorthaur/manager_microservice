package executor.service.processing.scenario

import executor.service.model.Scenario
import executor.service.redis.repository.ScenarioRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ScenarioProcessingServiceImpl(private val repo: ScenarioRepository) : ScenarioProcessingService {
    override fun add(scenario: Scenario) {
        repo.save(scenario)
    }

    override fun findAll(pageNum: Int, pageSize: Int) = repo.run {
        val request = PageRequest.of(pageNum, pageSize)
        findAll(request)
    }

    override fun findByName(pageNum: Int, pageSize: Int, name: String) = repo.run {
        val request = PageRequest.of(pageNum, pageSize)
        findPageByNameContaining(name, request)
    }

    override fun findBySite(pageNum: Int, pageSize: Int, site: String) = repo.run {
        val request = PageRequest.of(pageNum, pageSize)
        findPageBySiteContaining(site, request)
    }
}