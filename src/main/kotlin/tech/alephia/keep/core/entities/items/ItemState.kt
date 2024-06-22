package tech.alephia.keep.core.entities.items

import tech.alephia.keep.core.events.OpenContext
import tech.alephia.keep.core.events.Subscribable
import tech.alephia.keep.core.events.Subscriptions

class ItemState(
    val key: String,
    val name: String,
    val description: String,
    val indefiniteArticle: IndefiniteArticle,
    val canBeTaken: Boolean,
    private val subscriber: Subscriptions<Item>
): Subscribable<Item> by subscriber {
    private lateinit var item: Item

    fun setup(item: Item) {
        this.item = item
    }

    fun publish(key: String, context: OpenContext) {
        subscriber.publish(key, context.toEventContext(item))
    }
}
