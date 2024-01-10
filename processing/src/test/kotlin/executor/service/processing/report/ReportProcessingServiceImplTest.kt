package executor.service.processing.report

import executor.service.dao.repository.ReportRepository
import executor.service.model.ScenarioReport
import executor.service.processing.paging.Paginator
import executor.service.processing.query.QueryProcessor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.*
import org.springframework.data.domain.PageImpl
import java.time.LocalDateTime

private const val SCENARIO_ID = "some id"
private const val SCENARIO_NAME = "some name"
private const val SCENARIO_SITE = "some site"
private const val ERROR = "some error"
private const val PAGE_NUM = 0
private const val PAGE_SIZE = 10
internal class ReportProcessingServiceImplTest {
    private lateinit var repo: ReportRepository
    private lateinit var service: ReportProcessingService
    private lateinit var processor: QueryProcessor
    private lateinit var paginator: Paginator
    @BeforeEach
    fun init() {
        repo = mock()
        processor = mock()
        paginator = mock()
        service = ReportProcessingServiceImpl(repo, processor, paginator)
    }
    @Test
    fun testFindByScenarioId() {
        val expected = listOf(getReport(ERROR))
        whenever(repo.findByScenarioId(anyString())).thenReturn(expected)
        whenever(paginator.paginate(eq(expected), eq(PAGE_NUM), eq(PAGE_SIZE))).thenReturn(PageImpl(expected))
        val result = service.findByScenarioId(SCENARIO_ID, PAGE_NUM, PAGE_SIZE).content
        assertEquals(expected, result)
        verify(repo).findByScenarioId(anyString())
    }
    @Test
    fun testFindByName() {
        val expected = listOf(getReport())
        whenever(repo.searchByName(anyString())).thenReturn(expected)
        whenever(paginator.paginate(eq(expected), eq(PAGE_NUM), eq(PAGE_SIZE))).thenReturn(PageImpl(expected))
        whenever(processor.createPattern(eq(SCENARIO_NAME))).thenReturn(SCENARIO_NAME)
        val result = service.findByName(SCENARIO_NAME, PAGE_NUM, PAGE_SIZE).content
        assertEquals(expected, result)
        verify(repo).searchByName(anyString())
    }
    @Test
    fun testFindBySite() {
        val expected = listOf(getReport())
        whenever(repo.searchBySite(anyString())).thenReturn(expected)
        whenever(processor.createPattern(eq(SCENARIO_SITE))).thenReturn(SCENARIO_SITE)
        whenever(paginator.paginate(eq(expected), eq(PAGE_NUM), eq(PAGE_SIZE))).thenReturn(PageImpl(expected))
        val result = service.findBySite(SCENARIO_SITE, PAGE_NUM, PAGE_SIZE).content
        assertEquals(expected, result)
        verify(repo).searchBySite(anyString())
    }

    @Test
    fun testFindAll() {
        val expected = listOf(getReport())
        whenever(repo.findAll()).thenReturn(expected)
        whenever(paginator.paginate(eq(expected), eq(PAGE_NUM), eq(PAGE_SIZE))).thenReturn(PageImpl(expected))
        val result = service.findAll(PAGE_NUM, PAGE_SIZE).content
        assertEquals(expected, result)
        verify(repo).findAll()
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