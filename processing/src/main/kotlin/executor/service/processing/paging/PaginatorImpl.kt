package executor.service.processing.paging

import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class PaginatorImpl : Paginator {
    override fun <T> paginate(list: List<T>, pageNum: Int, pageSize: Int) =
        PageImpl(list, PageRequest.of(pageNum, pageSize), list.size.toLong())
}