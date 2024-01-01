package executor.service.controller

import executor.service.processing.report.ReportProcessingService
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/manager/reports")
@CrossOrigin("*")
class ReportController(private val service: ReportProcessingService) {
    @GetMapping("/{id}")
    fun findByScenarioId(@RequestParam pageNum: Int = 0, @RequestParam pageSize: Int = 10, @PathVariable id: String) =
        service.findByScenarioId(id, PageRequest.of(pageNum, pageSize))
    @GetMapping("/name={name}")
    fun findByName(@RequestParam pageNum: Int = 0, @RequestParam pageSize: Int = 10, @PathVariable name: String) =
        service.findByName(name, PageRequest.of(pageNum, pageSize))
    @GetMapping("/site={site}")
    fun findBySite(@RequestParam pageNum: Int = 0, @RequestParam pageSize: Int = 10, @PathVariable site: String) =
        service.findByName(site, PageRequest.of(pageNum, pageSize))
    @GetMapping
    fun findAll(@RequestParam pageNum: Int = 0, @RequestParam pageSize: Int = 10) = service.findAll(PageRequest.of(pageNum, pageSize))
}