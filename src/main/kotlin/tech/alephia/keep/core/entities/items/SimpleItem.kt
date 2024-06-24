package tech.alephia.keep.core.entities.items

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.entities.characters.Character
import tech.alephia.keep.core.events.EventContext
import tech.alephia.keep.core.events.OpenContext
import tech.alephia.keep.core.events.Subscribable
import tech.alephia.keep.core.events.Subscriptions

class SimpleItem(
    override val key: String,
    initialState: String,
    states: List<ItemState>,
    private val subscriber: Subscriptions<Item>
): Item, Subscribable<Item> by subscriber {
    private lateinit var game: Game
    private var statesByKey = states.associateBy { it.key }.toMap()
    private var state : ItemState = statesByKey[initialState]!!

    override fun setup(game: Game) {
        this.game = game;
        statesByKey.values.forEach { it.setup(this) }
        dispatch("on-enter")
    }

    override val name get() = state.name

    override val description get() = state.description

    override val indefiniteArticle get() = state.indefiniteArticle

    override val canBeTaken get() = state.canBeTaken

    override fun change(toState: String) {
        dispatch("on-exit")
        state = statesByKey[toState]!!
        dispatch("on-enter")
    }

    override fun publish(key: String, context: OpenContext) {
        val eventContext = context.toEventContext<Item>(this)

        state.publish(key, eventContext)
        subscriber.publish(key, eventContext)
    }

    private fun dispatch(key: String) {
        val eventContext = EventContext<Item>(
            this,
            null,
            game.scene,
            game,
            game.io
        )
        state.publish(key, eventContext)
    }
}
