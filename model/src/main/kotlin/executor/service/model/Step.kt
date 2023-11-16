package executor.service.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Reference
import org.springframework.data.redis.core.RedisHash

@RedisHash("steps")
data class Step(
    val action: String,
    val value: String,
    @Reference val report: StepReport?
) {
    @get:Id
    var id: String? = null
}

