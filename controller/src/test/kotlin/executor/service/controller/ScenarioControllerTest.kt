package executor.service.controller

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import executor.service.model.Scenario
import executor.service.processing.scenario.ScenarioProcessingService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
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
private const val SCENARIO_NAME = "some name"
private const val SCENARIO_SITE = "some site"
@SpringBootTest
@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc
internal class ScenarioControllerTest(@Autowired private val mockMvc: MockMvc) {

    @MockBean
    private lateinit var service: ScenarioProcessingService

    private val mapper = jacksonObjectMapper().apply { registerModule(JavaTimeModule()) }

    @Test
    fun testFindAll() {
        whenever(service.findAll(any(), any())).thenReturn(PageImpl(listOf()))
        val requestBuilder = get(BASE_URL)
        mockMvc.perform(requestBuilder).andExpect(status().isOk)
    }

    @Test
    fun testFindByName() {
        whenever(service.findByName(eq(SCENARIO_NAME), any(), any())).thenReturn(PageImpl(listOf()))
        val requestBuilder = get("$BASE_URL/name")
            .apply { param("name", SCENARIO_NAME) }
        mockMvc.perform(requestBuilder).andExpect(status().isOk)
    }

    @Test
    fun testFindBySite() {
        whenever(service.findBySite(eq(SCENARIO_SITE), any(), any())).thenReturn(PageImpl(listOf()))
        val requestBuilder = get("$BASE_URL/site")
            .apply { param("site", SCENARIO_SITE) }
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