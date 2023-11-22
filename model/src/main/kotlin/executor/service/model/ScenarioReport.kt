package executor.service.model

import com.redis.om.spring.annotations.Document
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.TimeToLive
import java.time.LocalTime

@Document
data class ScenarioReport(
    val name: String,
    val site: String,
    val stepsReports: List<StepReport>,
    val scenarioId: String,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val errorMessage: String?,
    val webDriverInfo: String
) {
    @get:Id
    var id: String? = null

    @TimeToLive
    val expiration: Long = 604800
}
