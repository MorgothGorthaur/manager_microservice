package executor.service.processing.paging

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class PaginatorImpl : Paginator {
    override fun <T> paginate(list: List<T>, pageNum: Int, pageSize: Int): Page<T> {
        val first = pageNum * pageSize
        val last = (pageNum + 1) * pageSize
        val pageContent = list.subList(first, last)
        return PageImpl(pageContent, PageRequest.of(pageNum, pageSize), list.size.toLong())
    }
}