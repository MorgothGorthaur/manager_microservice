package executor.service.model

import jakarta.validation.constraints.NotEmpty

data class Step(
    @field:NotEmpty val action: String,
    @field:NotEmpty val value: String
)
