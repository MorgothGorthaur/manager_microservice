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
    @GetMapping("/successful/name={name}")
    fun findSuccessfulByName(@RequestParam pageNum: Int, @RequestParam pageSize: Int, @PathVariable name:String) =
        service.findSuccessfulByName(name, PageRequest.of(pageNum, pageSize))
    @GetMapping("/successful/site={site}")
    fun findSuccessfulBySite(@RequestParam pageNum: Int, @RequestParam pageSize: Int, @PathVariable site:String) =
        service.findSuccessfulBySite(site, PageRequest.of(pageNum, pageSize))
    @GetMapping("/failed/name={name}")
    fun findFailedByName(@RequestParam pageNum: Int, @RequestParam pageSize: Int, @PathVariable name:String) =
        service.findFailedByName(name, PageRequest.of(pageNum, pageSize))
    @GetMapping("/failed/site={site}")
    fun findFailedBySite(@RequestParam pageNum: Int, @RequestParam pageSize: Int, @PathVariable site:String) =
        service.findFailedBySite(site, PageRequest.of(pageNum, pageSize))
}