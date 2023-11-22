package executor.service.redis.repository

import com.redis.om.spring.repository.RedisDocumentRepository
import executor.service.model.ScenarioReport

interface ReportRepository : RedisDocumentRepository<ScenarioReport, String>