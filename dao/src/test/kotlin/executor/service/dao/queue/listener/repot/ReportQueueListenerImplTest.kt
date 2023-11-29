package executor.service.dao.queue.listener.repot

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import executor.service.model.ScenarioReport
import executor.service.model.StepReport
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.data.redis.core.ListOperations
import org.springframework.data.redis.core.StringRedisTemplate
import java.time.LocalDateTime
import java.time.LocalTime

internal class ReportQueueListenerImplTest {

    private val mapper: ObjectMapper = jacksonObjectMapper().apply { registerModule(JavaTimeModule()) }
    private lateinit var template: StringRedisTemplate
    private lateinit var operations: ListOperations<String, String>
    private lateinit var listener: ReportQueueListener

    @BeforeEach
    fun init() {
        template = mock()
        operations = mock()
        listener = ReportQueueListenerImpl(template, mapper)
    }
    @Test
    fun poll() {
        val expected = getReport()
        val json = mapper.writeValueAsString(expected)
        whenever(template.opsForList()).thenReturn(operations)
        whenever(operations.rightPop(anyString())).thenReturn(json)
        val result = listener.poll()
        assertEquals(expected, result)
        verify(template).opsForList()
        verify(operations).rightPop(anyString())

    }

    private fun getReport(errorMessage: String? = null) = ScenarioReport(
        name = "some name",
        site = "some site",
        scenarioId = "some Id",
        startTime = LocalDateTime.now(),
        endTime = LocalDateTime.now(),
        webDriverInfo = "chrome driver info",
        stepReports = listOf(
            StepReport("action", "value", LocalTime.now(), LocalTime.now(), null),
            StepReport("action", "value", LocalTime.now(), LocalTime.now(), null),
            StepReport("action", "value", LocalTime.now(), LocalTime.now(), "some error"),

        ),
        errorMessage = errorMessage
    )
}