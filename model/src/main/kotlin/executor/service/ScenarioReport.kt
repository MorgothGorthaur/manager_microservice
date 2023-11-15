package executor.service

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.time.LocalTime

@RedisHash("scenario_reports")
data class ScenarioReport(
    val startTime: LocalTime,
    val endTime: LocalTime,
    val errorMessage: String?,
    val webDriverInfo: String
) {
    @get:Id
    var id: String? = null
}
