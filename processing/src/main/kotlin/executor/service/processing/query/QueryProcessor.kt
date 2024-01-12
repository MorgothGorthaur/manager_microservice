package executor.service.processing.query

internal interface QueryProcessor {
    /**
     * Generates a RedisSearch-compatible pattern from the given query.
     *
     * Replaces special characters, such as ":", with "`?`".
     * Adds "`*`" to both the beginning and end
     * if they are not already present for better search quality.
     *
     * @param[query] The input string query to be processed.
     * @return A search pattern string optimized for RedisSearch queries.
     */
    fun createPattern(query: String): String
}