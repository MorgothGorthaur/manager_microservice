package executor.service.controller

import executor.service.model.Scenario
import executor.service.processing.scenario.ScenarioProcessingService
import jakarta.validation.Valid
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/manager/scenarios")
@CrossOrigin("*")
class ScenarioController(private val service: ScenarioProcessingService) {

    @GetMapping
    fun findAll(@RequestParam pageNum: Int = 0, @RequestParam pageSize: Int = 10) = service.findAll(PageRequest.of(pageNum, pageSize))

    @GetMapping("/name={name}")
    fun findByName(@RequestParam pageNum: Int = 0, @RequestParam pageSize: Int = 10, @PathVariable name: String) =
        service.findByName(name, PageRequest.of(pageNum, pageSize))

    @GetMapping("/site={site}")
    fun findBySite(@RequestParam pageNum: Int = 0, @RequestParam pageSize: Int = 10, @PathVariable site: String) =
        service.findBySite(site, PageRequest.of(pageNum, pageSize))

    @PostMapping
    fun add(@Valid @RequestBody scenario: Scenario) { service.add(scenario) }

    @PatchMapping
    fun update(@Valid @RequestBody scenario: Scenario) {
        service.update(scenario)
    }

    @DeleteMapping
    fun delete(@RequestBody scenario: Scenario) {
        service.delete(scenario)
    }
}