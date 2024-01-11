package executor.service.dao.queue.consumer

interface QueueListener<T> {
    fun poll(): T?
}