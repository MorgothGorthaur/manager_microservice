package executor.service.processing.scenario

import executor.service.dao.repository.ScenarioRepository
import executor.service.model.Scenario
import executor.service.model.Step
import executor.service.processing.model.PageConfig
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable


internal class ScenarioProcessingServiceImplTest {

    private lateinit var repo: ScenarioRepository
    private lateinit var service: ScenarioProcessingService

    @BeforeEach
    fun init() {
        repo = mock()
        service = ScenarioProcessingServiceImpl(repo)
    }


    @Test
    fun add() {
        val scenario = getScenario()
        service.add(scenario)
        verify(repo).save(scenario)
    }

    @Test
    fun update() {
        val scenario = getScenario().apply { id = "someId" }
        service.update(scenario)
        verify(repo).update(scenario)
    }

    @Test
    fun delete() {
        val scenario = getScenario().apply { id = "someId" }
        service.delete(scenario)
        verify(repo).delete(scenario)
    }

    @Test
    fun findAll() {
        val pageConfig = PageConfig(10, 10)
        val expected = PageImpl(listOf<Scenario>())
        whenever(repo.findAll(any<Pageable>())).thenReturn(expected)
        val result = service.findAll(pageConfig)
        assertSame(expected, result)
        verify(repo).findAll(any<Pageable>())
    }

    @Test
    fun findByName() {
        val pageConfig = PageConfig(10, 10)
        val expected = PageImpl(listOf<Scenario>())
        whenever(repo.findPageByNameContaining(anyString(), any())).thenReturn(expected)
        val result = service.findByName("some name", pageConfig)
        assertSame(expected, result)
        verify(repo).findPageByNameContaining(anyString(), any())
    }

    @Test
    fun findBySite() {
        val pageConfig = PageConfig(10, 10)
        val expected = PageImpl(listOf<Scenario>())
        whenever(repo.findPageBySiteContaining(anyString(), any())).thenReturn(expected)
        val result = service.findBySite("some site", pageConfig)
        assertSame(expected, result)
        verify(repo).findPageBySiteContaining(anyString(), any())
    }

    private fun getScenario() = Scenario(
        "some scenario", "some site", listOf(
            Step("some action", "some value"),
            Step("some action", "some value"),
            Step("some action", "some value")
        )
    )
}