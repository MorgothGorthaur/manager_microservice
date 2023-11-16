package executor.service.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash("scenarios")
data class Scenario(
    @Indexed val name: String,
    @Indexed val site: String,
    val steps: List<Step>,
    val report: ScenarioReport
) {
    @get:Id
    var id: String? = null
}