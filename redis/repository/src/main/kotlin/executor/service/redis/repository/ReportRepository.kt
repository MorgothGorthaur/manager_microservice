package executor.service.redis.repository

import executor.service.model.ScenarioReport
import org.springframework.data.repository.CrudRepository

interface ReportRepository : CrudRepository<ScenarioReport, String>