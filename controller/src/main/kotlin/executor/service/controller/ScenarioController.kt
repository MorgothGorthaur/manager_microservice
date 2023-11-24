package executor.service.controller

import executor.service.processing.model.PageConfig
import executor.service.model.Scenario
import executor.service.processing.scenario.ScenarioProcessingService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/manager/scenarios")
class ScenarioController(private val service: ScenarioProcessingService) {

    @GetMapping
    fun findAll(@RequestBody pageConfig: PageConfig) = service.findAll(pageConfig)

    @GetMapping("/name={name}")
    fun findByName(@RequestBody pageConfig: PageConfig, @PathVariable name: String) =
        service.findByName(name, pageConfig)

    @GetMapping("/site={site}")
    fun findBySite(@RequestBody pageConfig: PageConfig, @PathVariable site: String) =
        service.findBySite(site, pageConfig)

    @PostMapping
    fun add(@RequestBody scenario: Scenario) {service.add(scenario)}

    @PatchMapping
    fun update(@RequestBody scenario: Scenario) {service.update(scenario)}

    @DeleteMapping
    fun delete(@RequestBody scenario: Scenario) {service.delete(scenario)}
}