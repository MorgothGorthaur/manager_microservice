package executor.service.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.time.LocalTime

@RedisHash("step_reports")
data class StepReport(
    val startTime: LocalTime,
    val endTime: LocalTime,
    val errorMessage: String?
) {
    @get:Id
    var id: String? = null
}
