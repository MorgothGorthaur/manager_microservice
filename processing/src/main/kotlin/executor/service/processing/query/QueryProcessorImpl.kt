package executor.service.processing.query

import org.springframework.stereotype.Component

@Component
internal class QueryProcessorImpl : QueryProcessor {
    override fun createPattern(query: String): String {
        var res = query.replace("[:@\\[\\]()]".toRegex(), "?")
        if (!res.endsWith("*") && !res.endsWith("/") && !res.endsWith("?")) res += "*"
        if (!res.startsWith("*") && !res.startsWith("/") && !res.startsWith("?")) res = "*$res"
        return res
    }
}