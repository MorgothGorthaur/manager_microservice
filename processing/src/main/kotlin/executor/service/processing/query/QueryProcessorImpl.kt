package executor.service.processing.query

import org.springframework.stereotype.Component

@Component
class QueryProcessorImpl : QueryProcessor {
    override fun createPattern(query: String): String {
        var res = query.replace(":", "*")
        if (!res.endsWith("*")) res += "*"
        if (!res.startsWith("")) res = "*$res"
        return res
    }
}