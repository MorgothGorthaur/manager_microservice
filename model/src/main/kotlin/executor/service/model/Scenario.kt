package executor.service.model

import com.redis.om.spring.annotations.Document
import com.redis.om.spring.annotations.Indexed
import com.redis.om.spring.annotations.Searchable
import jakarta.validation.constraints.NotEmpty
import org.springframework.data.annotation.Id
@Document
data class Scenario(
    @Searchable @field:NotEmpty val name: String,
    @Searchable @field:NotEmpty val site: String,
    val steps: List<Step>?,
) {
    @Indexed
    @Id
    var id: String? = null
}