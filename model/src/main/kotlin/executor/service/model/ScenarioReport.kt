package executor.service.model

import java.time.LocalTime

data class ScenarioReport(
    val startTime: LocalTime,
    val endTime: LocalTime,
    val errorMessage: String?,
    val webDriverInfo: String
)
