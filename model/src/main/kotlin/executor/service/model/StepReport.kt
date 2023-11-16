package executor.service.model

import java.time.LocalTime

data class StepReport(
    val startTime: LocalTime,
    val endTime: LocalTime,
    val errorMessage: String?
)
