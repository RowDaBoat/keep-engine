package tech.alephia.keep.core.entities.characters

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.events.OpenContext
import tech.alephia.keep.core.events.Subscribable
import tech.alephia.keep.core.events.Subscriptions
import tech.alephia.keep.core.storages.ItemStorage

class NPC(
    override val key: String,
    override val name: String,
    override val description: String,
    override val inventory: ItemStorage,
    private val subscriber: Subscriptions<Character>
): Character, Subscribable<Character> by subscriber {
    override fun setup(game: Game) {
    }

    override fun showAsDefinite() = name

    override fun showAsIndefinite() = name

    override fun publish(key: String, context: OpenContext) {
        subscriber.publish(key, context.toEventContext(this))
    }

    override fun change(toState: String) {
    }
}
