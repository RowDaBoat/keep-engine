package tech.alephia.keep.core.events

interface Subscribable<T> {
    fun subscribe(key: String, callback: EventContext<T>.() -> Unit)
}
