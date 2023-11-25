package executor.service.dao.queue.listener

interface QueueListener<T> {
    fun poll(): T?
}