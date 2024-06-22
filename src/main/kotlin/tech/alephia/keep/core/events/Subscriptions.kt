package tech.alephia.keep.core.events

class Subscriptions<T>(default: Map<String, EventContext<T>.() -> Unit>? = null): Subscribable<T> {
    private val subscriptions : MutableMap<String, EventContext<T>.() -> Unit> =
        default?.toMutableMap() ?: mutableMapOf()

    fun publish(key: String, actionContext: EventContext<T>) {
        subscriptions[key]?.invoke(actionContext)
    }

    override fun subscribe(key: String, callback: EventContext<T>.() -> Unit) {
        subscriptions[key] = callback
    }
}