package executor.service.controller

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
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

private const val BASE_URL = "/manager/reports"
private const val SCENARIO_ID = "some id"
private const val SCENARIO_NAME = "some name"
private const val SCENARIO_SITE = "some site"

@SpringBootTest
@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc
internal class ReportControllerTest(@Autowired private val mockMvc: MockMvc) {

    @MockBean
    private lateinit var service: ReportProcessingService

    @Test
    fun testFindByScenarioId() {
        whenever(service.findByScenarioId(eq(SCENARIO_ID), any())).thenReturn(PageImpl(listOf()))
        val requestBuilder = MockMvcRequestBuilders.get("$BASE_URL/id")
            .apply { param("id", SCENARIO_ID) }
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun testFindByName() {
        whenever(service.findByName(eq(SCENARIO_NAME), any())).thenReturn(PageImpl(listOf()))
        val requestBuilder = MockMvcRequestBuilders.get("$BASE_URL/name")
            .apply { param("name", SCENARIO_NAME) }
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun testFindBySite() {
        whenever(service.findBySite(eq(SCENARIO_SITE), any())).thenReturn(PageImpl(listOf()))
        val requestBuilder = MockMvcRequestBuilders.get("$BASE_URL/site")
            .apply { param("site", SCENARIO_SITE) }
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun testFindAll() {
        whenever(service.findAll(any())).thenReturn(PageImpl(listOf()))
        val requestBuilder =  MockMvcRequestBuilders.get(BASE_URL)
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk)
    }
}