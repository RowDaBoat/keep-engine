package tech.alephia.keep.core.entities.items

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.events.EventContext
import tech.alephia.keep.core.events.OpenContext
import tech.alephia.keep.core.events.Subscribable

class StatefulItem(
    override val key: String,
    initialState: String,
    states: List<ItemState>
): Item, Subscribable<Item> {
    private lateinit var game: Game
    private var statesByKey = states.associateBy { it.key }.toMap()
    private var state : ItemState = statesByKey[initialState.also { println("ititit: $it") }]!!

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

    override fun publish(key: String, context: OpenContext) =
        state.publish(key, context)

    override fun subscribe(key: String, callback: EventContext<Item>.() -> Unit) =
        state.subscribe(key, callback)

    private fun dispatch(key: String) {
        state.publish(key, OpenContext(game.scene, game, game.io))
    }
}
