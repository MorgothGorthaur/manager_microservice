package executor.service.model

import com.redis.om.spring.annotations.Document
import com.redis.om.spring.annotations.Indexed
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.TimeToLive
import java.time.LocalDateTime

@Document
data class ScenarioReport(
    val name: String,
    val site: String,
    val stepsReports: List<StepReport>,
    @Indexed val scenarioId: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val errorMessage: String?,
    val webDriverInfo: String
) {
    @Indexed
    @Id
    var id: String? = null

    @TimeToLive
    val expiration: Long = 604800
}
