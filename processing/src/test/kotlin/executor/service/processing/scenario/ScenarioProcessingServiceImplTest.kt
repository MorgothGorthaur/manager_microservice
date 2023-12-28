package executor.service.processing.scenario

import executor.service.dao.repository.ScenarioRepository
import executor.service.model.Scenario
import org.junit.jupiter.api.Assertions.assertSame
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


internal class ScenarioProcessingServiceImplTest {

    private lateinit var repo: ScenarioRepository
    private lateinit var service: ScenarioProcessingService
    private lateinit var request: PageRequest

    @BeforeEach
    fun init() {
        repo = mock()
        service = ScenarioProcessingServiceImpl(repo)
        request = mock()
    }


    @Test
    fun testAdd() {
        val scenario = getScenario()
        service.add(scenario)
        verify(repo).save(scenario)
    }

    @Test
    fun testUpdate() {
        val scenario = getScenario().apply { id = "someId" }
        service.update(scenario)
        verify(repo).update(scenario)
    }

    @Test
    fun testDelete() {
        val scenario = getScenario().apply { id = "someId" }
        service.delete(scenario)
        verify(repo).delete(scenario)
    }

    @Test
    fun tetFindAll() {
        val expected = PageImpl(listOf<Scenario>())
        whenever(repo.findAll(any<Pageable>())).thenReturn(expected)
        val result = service.findAll(request)
        assertSame(expected, result)
        verify(repo).findAll(any<Pageable>())
    }

    @Test
    fun testFindByName() {
        val expected = PageImpl(listOf<Scenario>())
        whenever(repo.searchByName(anyString(), any())).thenReturn(expected)
        val result = service.findByName("some name", request)
        assertSame(expected, result)
        verify(repo).searchByName(anyString(), any())
    }

    @Test
    fun testFindBySite() {
        val expected = PageImpl(listOf<Scenario>())
        whenever(repo.searchBySite(anyString(), any())).thenReturn(expected)
        val result = service.findBySite("some site", request)
        assertSame(expected, result)
        verify(repo).searchBySite(anyString(), any())
    }

    private fun getScenario() = Scenario(
        name = "some scenario",
        site = "some site",
        steps = listOf()
    )
}