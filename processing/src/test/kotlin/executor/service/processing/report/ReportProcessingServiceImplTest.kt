package executor.service.processing.report

import executor.service.dao.repository.ReportRepository
import executor.service.model.ScenarioReport
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

private const val SCENARIO_ID = "some id"

private const val SCENARIO_NAME = "some name"

private const val SCENARIO_SITE = "some site"

private const val ERROR = "some error"

internal class ReportProcessingServiceImplTest {
    private lateinit var repo: ReportRepository
    private lateinit var service: ReportProcessingService
    private lateinit var request: PageRequest
    @BeforeEach
    fun init() {
        repo = mock()
        service = ReportProcessingServiceImpl(repo)
        request = mock()
    }
    @Test
    fun testFindByScenarioId() {
        val expected = listOf(getReport(ERROR))
        whenever(repo.findPageByScenarioId(anyString(), any())).thenReturn(PageImpl(expected))
        val result = service.findByScenarioId(SCENARIO_ID, request).content
        assertEquals(expected, result)
        verify(repo).findPageByScenarioId(anyString(), any())
    }
    @Test
    fun testFindByName() {
        val expected = listOf(getReport())
        whenever(repo.searchByName(anyString(), any())).thenReturn(PageImpl(expected))
        val result = service.findByName(SCENARIO_NAME, request).content
        assertEquals(expected, result)
        verify(repo).searchByName(anyString(), any())
    }
    @Test
    fun testFindBySite() {
        val expected = listOf(getReport())
        whenever(repo.searchBySite(anyString(), any())).thenReturn(PageImpl(expected))
        val result = service.findBySite(SCENARIO_SITE, request).content
        assertEquals(expected, result)
        verify(repo).searchBySite(anyString(), any())
    }

    @Test
    fun testFindAll() {
        val expected = listOf(getReport())
        whenever(repo.findAll(any<Pageable>())).thenReturn(PageImpl(expected))
        val result = service.findAll(request).content
        assertEquals(expected, result)
        verify(repo).findAll(any<Pageable>())
    }

    private fun getReport(errorMessage: String? = null) = ScenarioReport(
        name = SCENARIO_NAME,
        site = SCENARIO_SITE,
        scenarioId = SCENARIO_ID,
        startTime = LocalDateTime.now(),
        endTime = LocalDateTime.now(),
        webDriverInfo = "chrome driver info",
        stepReports = listOf(),
        errorMessage = errorMessage
    )

}