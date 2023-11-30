package executor.service.controller

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import executor.service.processing.model.PageConfig
import executor.service.processing.report.ReportProcessingService
import org.junit.jupiter.api.Assertions.*
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

private const val BASE_URL = "/manager/reports"
private const val SCENARIO_ID = "some id"

@SpringBootTest
@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc
internal class ReportControllerTest(@Autowired private val mockMvc: MockMvc)  {

    @MockBean private lateinit var service: ReportProcessingService
    private val mapper = jacksonObjectMapper().apply { registerModule(JavaTimeModule()) }

    @Test
    fun testFindByScenarioId() {
        val pageConfig = PageConfig(10, 10)
        whenever(service.findByScenarioId(SCENARIO_ID, pageConfig)).thenReturn(PageImpl(listOf()))
        val requestBuilder = MockMvcRequestBuilders.get("$BASE_URL/$SCENARIO_ID").apply {
            contentType(MediaType.APPLICATION_JSON)
            content(mapper.writeValueAsString(pageConfig))
        }
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun testFindLastByScenarioId() {
        whenever(service.findByScenarioId(eq(SCENARIO_ID), any())).thenReturn(PageImpl(listOf()))
        val requestBuilder = MockMvcRequestBuilders.get("$BASE_URL/$SCENARIO_ID/last")
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun testFindSuccessful() {
        val pageConfig = PageConfig(10, 10)
        whenever(service.findSuccessful(SCENARIO_ID, pageConfig)).thenReturn(PageImpl(listOf()))
        val requestBuilder = MockMvcRequestBuilders.get("$BASE_URL/$SCENARIO_ID/successful").apply {
            contentType(MediaType.APPLICATION_JSON)
            content(mapper.writeValueAsString(pageConfig))
        }
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun testFindFailed() {
        val pageConfig = PageConfig(10, 10)
        whenever(service.findFailed(SCENARIO_ID, pageConfig)).thenReturn(PageImpl(listOf()))
        val requestBuilder = MockMvcRequestBuilders.get("$BASE_URL/$SCENARIO_ID/failed").apply {
            contentType(MediaType.APPLICATION_JSON)
            content(mapper.writeValueAsString(pageConfig))
        }
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk)
    }
}