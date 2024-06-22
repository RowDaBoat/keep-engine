package tech.alephia.keep.core.entities.characters

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.events.OpenContext
import tech.alephia.keep.core.events.Subscribable
import tech.alephia.keep.core.events.Subscriptions
import tech.alephia.keep.core.storages.ItemStorage

class StatefulNPC(
    override val key: String,
    override val inventory: ItemStorage,
    initialState: String,
    states: List<CharacterState>,
    private val subscriber: Subscriptions<Character>
): Character, Subscribable<Character> by subscriber {
    private lateinit var game: Game
    private var statesByKey = states.associateBy { it.key }.toMap()
    private var state : CharacterState = statesByKey[initialState]!!

    override fun setup(game: Game) {
        this.game = game;
        statesByKey.values.forEach { it.setup(this) }
    }

    override val name get() = state.name

    override val description get() = state.description

    override fun showAsDefinite() = name

    override fun showAsIndefinite() = name

    override fun change(toState: String) {
        dispatch("on-enter")
        state = statesByKey[toState]!!
        dispatch("on-exit")
    }

    override fun publish(key: String, context: OpenContext) {
        subscriber.publish(key, context.toEventContext(this))
    }

    private fun dispatch(key: String) {
        state.publish(key, OpenContext(game.scene, game, game.io))
    }
}
