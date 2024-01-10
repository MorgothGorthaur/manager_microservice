package executor.service.processing.scenario

import executor.service.dao.repository.ReportRepository
import executor.service.dao.repository.ScenarioRepository
import executor.service.model.Scenario
import executor.service.processing.paging.Paginator
import executor.service.processing.query.QueryProcessor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.data.domain.PageImpl


private const val SCENARIO_NAME = "some name"
private const val SCENARIO_SITE = "some site"
private const val PAGE_NUM = 0
private const val PAGE_SIZE = 10

private const val SCENARIO_ID = "someId"

internal class ScenarioProcessingServiceImplTest {

    private lateinit var scenarioRepository: ScenarioRepository
    private lateinit var reportRepository: ReportRepository
    private lateinit var service: ScenarioProcessingService
    private lateinit var processor: QueryProcessor
    private lateinit var paginator: Paginator

    @BeforeEach
    fun init() {
        scenarioRepository = mock()
        reportRepository = mock()
        processor = mock()
        paginator = mock()
        service = ScenarioProcessingServiceImpl(scenarioRepository, reportRepository, processor, paginator)
    }


    @Test
    fun testAdd() {
        val scenario = getScenario()
        service.add(scenario)
        verify(scenarioRepository).save(scenario)
    }

    @Test
    fun testUpdate() {
        val scenario = getScenario().apply { id = SCENARIO_ID }
        service.update(scenario)
        verify(scenarioRepository).update(scenario)
    }

    @Test
    fun testDelete() {
        val scenario = getScenario().apply { id = SCENARIO_ID }
        service.delete(scenario)
        verify(scenarioRepository).delete(scenario)
        verify(reportRepository).deleteByScenarioId(scenario.id!!)
    }

    @Test
    fun tetFindAll() {
        val expected =listOf<Scenario>()
        whenever(scenarioRepository.findAll()).thenReturn(expected)
        whenever(paginator.paginate(eq(expected), eq(PAGE_NUM), eq(PAGE_SIZE))).thenReturn(PageImpl(expected))
        val result = service.findAll(PAGE_NUM, PAGE_SIZE).content
        assertEquals(expected, result)
        verify(scenarioRepository).findAll()
    }

    @Test
    fun testFindByName() {
        val expected = listOf<Scenario>()
        whenever(scenarioRepository.searchByName(anyString())).thenReturn(expected)
        whenever(paginator.paginate(eq(expected), eq(PAGE_NUM), eq(PAGE_SIZE))).thenReturn(PageImpl(expected))
        whenever(processor.createPattern(eq(SCENARIO_NAME))).thenReturn(SCENARIO_NAME)
        val result = service.findByName(SCENARIO_NAME, PAGE_NUM, PAGE_SIZE).content
        assertEquals(expected, result)
        verify(scenarioRepository).searchByName(anyString())
    }

    @Test
    fun testFindBySite() {
        val expected = listOf<Scenario>()
        whenever(scenarioRepository.searchBySite(anyString())).thenReturn(expected)
        whenever(processor.createPattern(eq(SCENARIO_SITE))).thenReturn(SCENARIO_SITE)
        whenever(paginator.paginate(eq(expected), eq(PAGE_NUM), eq(PAGE_SIZE))).thenReturn(PageImpl(expected))
        val result = service.findBySite(SCENARIO_SITE, PAGE_NUM, PAGE_SIZE).content
        assertEquals(expected, result)
        verify(scenarioRepository).searchBySite(anyString())
    }

    private fun getScenario() = Scenario(
        name = SCENARIO_NAME,
        site = SCENARIO_SITE,
        steps = listOf()
    )
}