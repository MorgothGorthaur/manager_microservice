package executor.service.processing.query

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class QueryProcessorImplTest {
    @Test
    fun testCreatePattern_ReplacesCharacters() {
        val queryProcessor = QueryProcessorImpl()
        val result = queryProcessor.createPattern("t@e:s[]t()")
        assertEquals("*t?e?s??t??", result)
    }

    @Test
    fun testCreatePattern_BothConditionsMet() {
        val queryProcessor = QueryProcessorImpl()
        val result = queryProcessor.createPattern("test")
        assertEquals("*test*", result)
    }

    @Test
    fun testCreatePattern_ConditionsNotMet() {
        val queryProcessor = QueryProcessorImpl()
        val result = queryProcessor.createPattern("/test?")
        assertEquals("/test?", result)
    }
}