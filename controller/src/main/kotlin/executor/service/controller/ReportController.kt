package executor.service.controller

import executor.service.processing.report.ReportProcessingService
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/manager/reports")
@CrossOrigin("*")
class ReportController(private val service: ReportProcessingService) {

    @GetMapping("/{id}")
    fun findByScenarioId(@RequestParam pageNum: Int, @RequestParam pageSize: Int, @PathVariable id: String) =
        service.findByScenarioId(id, PageRequest.of(pageNum, pageSize))

    @GetMapping("/{id}/last")
    fun findLastByScenarioId(@PathVariable id: String) =
        service.findByScenarioId(id, PageRequest.of(0, 10)).content.toList()

    @GetMapping("/{id}/successful")
    fun findSuccessful(@RequestParam pageNum: Int, @RequestParam pageSize: Int, @PathVariable id: String) =
        service.findSuccessful(id, PageRequest.of(pageNum, pageSize))

    @GetMapping("/{id}/failed")
    fun findFailed(@RequestParam pageNum: Int, @RequestParam pageSize: Int, @PathVariable id: String) =
        service.findFailed(id, PageRequest.of(pageNum, pageSize))
}