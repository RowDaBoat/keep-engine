package tech.alephia.keep.core.events

interface Publisher {
    fun publish(key: String, context: OpenContext)
}
