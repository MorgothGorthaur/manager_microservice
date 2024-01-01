package executor.service.model

import com.redis.om.spring.annotations.Document
import com.redis.om.spring.annotations.Indexed
import com.redis.om.spring.annotations.Searchable
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.TimeToLive
import java.time.LocalDateTime

@Document
data class ScenarioReport(
    @Searchable
    val name: String,
    @Searchable
    val site: String,
    val stepReports: List<StepReport>,
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
