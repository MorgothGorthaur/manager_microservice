package executor.service.model

import com.redis.om.spring.annotations.Document
import com.redis.om.spring.annotations.Searchable
import org.springframework.data.annotation.Id
@Document
data class Scenario(
    @Searchable val name: String,
    @Searchable val site: String,
    val steps: List<Step>?,
) {
    @get:Id
    var id: String? = null
}