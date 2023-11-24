package executor.service.controller

import executor.service.processing.model.PageConfig
import executor.service.processing.report.ReportProcessingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/manager/reports")
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