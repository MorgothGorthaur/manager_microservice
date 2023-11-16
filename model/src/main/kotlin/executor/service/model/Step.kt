package executor.service.model

data class Step(
    val action: String,
    val value: String,
    val report: StepReport?
)
