package executor.service.controller

import executor.service.processing.model.PageConfig
import executor.service.processing.report.ReportProcessingService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/manager/reports")
@CrossOrigin("*")
class ReportController(private val service: ReportProcessingService) {

    @GetMapping("/{id}")
    fun findByScenarioId(@RequestBody pageConfig: PageConfig, @PathVariable id: String) =
        service.findByScenarioId(id, pageConfig)

    @GetMapping("/{id}/last")
    fun findLastByScenarioId(@PathVariable id: String) =
        service.findByScenarioId(id, PageConfig(0, 10)).content.toList()

    @GetMapping("/{id}/successful")
    fun findSuccessful(@RequestBody pageConfig: PageConfig, @PathVariable id: String) =
        service.findSuccessful(id, pageConfig)

    @GetMapping("/{id}/failed")
    fun findFailed(@RequestBody pageConfig: PageConfig, @PathVariable id: String) =
        service.findFailed(id, pageConfig)
}