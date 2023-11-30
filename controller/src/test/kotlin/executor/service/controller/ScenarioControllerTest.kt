package executor.service.controller

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import executor.service.model.Scenario
import executor.service.processing.model.PageConfig
import executor.service.processing.scenario.ScenarioProcessingService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.PageImpl
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

private const val BASE_URL = "/manager/scenarios"

@SpringBootTest
@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc
internal class ScenarioControllerTest(@Autowired private val mockMvc: MockMvc) {

    @MockBean private lateinit var service: ScenarioProcessingService

    private val mapper = jacksonObjectMapper().apply { registerModule(JavaTimeModule()) }

    @Test
    fun testFindAll() {
        val pageConfig = PageConfig(10, 10)
        whenever(service.findAll(pageConfig)).thenReturn(PageImpl(listOf()))
        val requestBuilder = get(BASE_URL).apply {
            contentType(MediaType.APPLICATION_JSON)
            content(mapper.writeValueAsString(pageConfig))
        }
        mockMvc.perform(requestBuilder).andExpect(status().isOk)
    }

    @Test
    fun testFindByName() {
        val name = "some name"
        val pageConfig = PageConfig(10, 10)
        whenever(service.findByName(name, pageConfig)).thenReturn(PageImpl(listOf()))
        val requestBuilder = get("$BASE_URL/name=$name").apply {
            contentType(MediaType.APPLICATION_JSON)
            content(mapper.writeValueAsString(pageConfig))
        }
        mockMvc.perform(requestBuilder).andExpect(status().isOk)
    }

    @Test
    fun testFindBySite() {
        val site = "some site"
        val pageConfig = PageConfig(10, 10)
        whenever(service.findBySite(site, pageConfig)).thenReturn(PageImpl(listOf()))
        val requestBuilder = get("$BASE_URL/site=$site").apply {
            contentType(MediaType.APPLICATION_JSON)
            content(mapper.writeValueAsString(pageConfig))
        }
        mockMvc.perform(requestBuilder).andExpect(status().isOk)
    }

    @Test
    fun testAdd() {
        val scenario = getScenario()
        val requestBuilder = post(BASE_URL).apply {
            contentType(MediaType.APPLICATION_JSON)
            content(mapper.writeValueAsString(scenario))
        }
        mockMvc.perform(requestBuilder).andExpect(status().isOk)
    }

    @Test
    fun testUpdate() {
        val scenario = getScenario().apply { id = "some id" }
        val requestBuilder = patch(BASE_URL).apply {
            contentType(MediaType.APPLICATION_JSON)
            content(mapper.writeValueAsString(scenario))
        }
        mockMvc.perform(requestBuilder).andExpect(status().isOk)
    }

    @Test
    fun testDelete() {
        val scenario = getScenario().apply { id = "some id" }
        val requestBuilder = delete(BASE_URL).apply {
            contentType(MediaType.APPLICATION_JSON)
            content(mapper.writeValueAsString(scenario))
        }
        mockMvc.perform(requestBuilder).andExpect(status().isOk)
    }

    private fun getScenario() = Scenario(
        name = "some scenario",
        site = "some site",
        steps = listOf()
    )
}