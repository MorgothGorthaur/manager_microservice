package executor.service.dao.queue.consumer

interface QueueConsumer<T> {
    fun poll(): T?
}