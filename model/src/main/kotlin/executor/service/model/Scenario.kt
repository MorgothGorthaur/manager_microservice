package executor.service.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Reference
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash("scenarios")
data class Scenario(
    @Indexed val name: String,
    @Indexed val site: String,
    @Reference val steps: List<Step>,
    @Reference val report: ScenarioReport
) {
    @get:Id
    var id: String? = null
}