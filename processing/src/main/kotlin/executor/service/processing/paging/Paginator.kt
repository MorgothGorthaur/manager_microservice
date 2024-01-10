package executor.service.processing.paging

import org.springframework.data.domain.Page

interface Paginator {
    fun <T> paginate(list: List<T>, pageNum: Int, pageSize: Int): Page<T>
}