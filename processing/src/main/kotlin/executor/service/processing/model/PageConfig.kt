package executor.service.processing.model

import org.springframework.data.domain.PageRequest

data class PageConfig(val pageNum: Int, val pageSize: Int) {
    fun request() = PageRequest.of(pageNum, pageSize)
}
