package executor.service.processing.paging

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PaginatorImplTest {
    private var paginator: Paginator = PaginatorImpl()

    @Test
    fun testPaginate() {
        val pageNum = 0
        val pageSize = 3
        val data = listOf(1, 2, 3, 4, 5)
        val expected = listOf(1, 2, 3)
        val result = paginator.paginate(data, pageNum, pageSize).content
        assertEquals(expected, result)
    }

    @Test
    fun testPaginate_whenPageSizeISBiggerThenDataSize() {
        val pageNum = 0
        val pageSize = 7
        val data = listOf(1, 2, 3, 4, 5)
        val expected = listOf(1, 2, 3, 4, 5)
        val result = paginator.paginate(data, pageNum, pageSize).content
        assertEquals(expected, result)
    }

    @Test
    fun testPaginate_shouldThrowIllegalArgumentException() {
        val pageNum = 0
        val pageSize = -7
        val data = listOf(1, 2, 3, 4, 5)
        assertThrows<IllegalArgumentException> {  paginator.paginate(data, pageNum, pageSize) }
    }

    @Test
    fun testPaginate_returnEmpty() {
        val pageNum = 4
        val pageSize = 3
        val data = listOf(1, 2, 3, 4, 5)
        val expected = listOf<Int>()
        val result = paginator.paginate(data, pageNum, pageSize).content
        assertEquals(expected, result)
    }
}