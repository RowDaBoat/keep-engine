package tech.alephia.keep.core.entities.items

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.events.OpenContext
import tech.alephia.keep.core.events.Subscribable
import tech.alephia.keep.core.events.Subscriptions

class SomeItem(
    override val key: String,
    override val name: String,
    override val description: String,
    override val indefiniteArticle: IndefiniteArticle,
    override val canBeTaken: Boolean = false,
    private val subscriber: Subscriptions<Item>
) : Item, Subscribable<Item> by subscriber {
    private lateinit var game: Game

    override fun setup(game: Game) {
        this.game = game
    }

    override fun change(toState: String) {}

    override fun publish(key: String, context: OpenContext) {
        subscriber.publish(key, context.toEventContext(this))
    }
}
