package executor.service.processing.report

import executor.service.dao.repository.ReportRepository
import executor.service.model.ScenarioReport
import executor.service.processing.model.PageConfig
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.data.domain.PageImpl
import java.time.LocalDateTime

private const val SCENARIO_ID = "some id"

internal class ReportProcessingServiceImplTest {

    private lateinit var repo: ReportRepository
    private lateinit var service: ReportProcessingService


    @BeforeEach
    fun init() {
        repo = mock()
        service = ReportProcessingServiceImpl(repo)
    }

    @Test
    fun testFindByScenarioId() {
        val expected = listOf(getReport("some error"))
        val pageConfig = PageConfig(10, 10)
        whenever(repo.findPageByScenarioId(anyString(), any())).thenReturn(PageImpl(expected))
        val result = service.findByScenarioId(SCENARIO_ID, pageConfig).content
        assertEquals(expected, result)
        verify(repo).findPageByScenarioId(anyString(), any())
    }

    @Test
    fun testFindSuccessful() {
        val expected = listOf(getReport())
        val pageConfig = PageConfig(10, 10)
        whenever(repo.findPageByScenarioIdAndErrorMessageIsNull(anyString(), any())).thenReturn(PageImpl(expected))
        val result = service.findSuccessful(SCENARIO_ID, pageConfig).content
        assertEquals(expected, result)
        verify(repo).findPageByScenarioIdAndErrorMessageIsNull(anyString(), any())
    }

    @Test
    fun testFindFailed() {
        val expected = listOf(getReport("some error"))
        val pageConfig = PageConfig(10, 10)
        whenever(repo.findPageByScenarioIdAndErrorMessageIsNotNull(anyString(), any())).thenReturn(PageImpl(expected))
        val result = service.findFailed(SCENARIO_ID, pageConfig).content
        assertEquals(expected, result)
        verify(repo).findPageByScenarioIdAndErrorMessageIsNotNull(anyString(), any())
    }

    private fun getReport(errorMessage: String? = null) = ScenarioReport(
        name = "some name",
        site = "some site",
        scenarioId = SCENARIO_ID,
        startTime = LocalDateTime.now(),
        endTime = LocalDateTime.now(),
        webDriverInfo = "chrome driver info",
        stepReports = listOf(),
        errorMessage = errorMessage
    )

}