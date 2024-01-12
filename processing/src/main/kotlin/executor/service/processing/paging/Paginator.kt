package executor.service.processing.paging

import org.springframework.data.domain.Page

internal interface Paginator {
    fun <T> paginate(list: List<T>, pageNum: Int, pageSize: Int): Page<T>
}