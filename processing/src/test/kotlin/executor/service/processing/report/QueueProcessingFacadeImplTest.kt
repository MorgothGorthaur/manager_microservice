package executor.service.processing.report

import executor.service.dao.queue.listener.repot.ReportQueueListener
import executor.service.dao.repository.ReportRepository
import executor.service.model.ScenarioReport
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.time.LocalDateTime

internal class QueueProcessingFacadeImplTest {

    private lateinit var repo: ReportRepository
    private lateinit var listener: ReportQueueListener
    private lateinit var facade: QueueProcessingFacade

    @BeforeEach
    fun init() {
        repo = mock()
        listener = mock()
        facade = QueueProcessingFacadeImpl(listener, repo)
    }

    @Test
    fun execute() {
        whenever(listener.poll()).thenReturn(getReport(), getReport(), getReport(), null)
        facade.execute()
        verify(listener, times(4)).poll()
        verify(repo, times(3)).save(any())
    }

    private fun getReport() = ScenarioReport(
        name = "some name",
        site = "some site",
        scenarioId = "some id",
        startTime = LocalDateTime.now(),
        endTime = LocalDateTime.now(),
        webDriverInfo = "chrome driver info",
        stepReports = listOf(),
        errorMessage = "some error"
    )


}