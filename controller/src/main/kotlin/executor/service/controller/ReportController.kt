package executor.service.controller

import executor.service.processing.report.ReportProcessingService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/manager/reports")
@CrossOrigin("*")
class ReportController(private val service: ReportProcessingService) {
    @GetMapping("/id")
    fun findByScenarioId(
        @RequestParam(defaultValue = "0") pageNum: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam id: String
    ) = service.findByScenarioId(id, pageNum, pageSize)

    @GetMapping("/name")
    fun findByName(
        @RequestParam(defaultValue = "0") pageNum: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam name: String
    ) = service.findByName(name, pageNum, pageSize)

    @GetMapping("/site")
    fun findBySite(
        @RequestParam(defaultValue = "0") pageNum: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam site: String
    ) = service.findBySite(site, pageNum, pageSize)

    @GetMapping
    fun findAll(
        @RequestParam(defaultValue = "0") pageNum: Int,
        @RequestParam(defaultValue = "10") pageSize: Int
    ) = service.findAll(pageNum, pageSize)
}