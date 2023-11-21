package executor.service.model

import java.time.LocalTime

data class StepReport(
    val action: String,
    val value: String,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val errorMessage: String?
)
