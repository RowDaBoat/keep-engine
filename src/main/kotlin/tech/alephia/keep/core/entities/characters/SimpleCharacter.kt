package tech.alephia.keep.core.entities.characters

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.events.EventContext
import tech.alephia.keep.core.events.OpenContext
import tech.alephia.keep.core.events.Subscribable
import tech.alephia.keep.core.events.Subscriptions
import tech.alephia.keep.core.storages.ItemStorage

class SimpleCharacter(
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
        this.game = game
        inventory.setup(game)
        statesByKey.values.forEach { it.setup(this) }
        dispatch("on-enter")
    }

    override val name get() = state.name

    override val description get() = state.description

    override fun showAsDefinite() = name

    override fun showAsIndefinite() = name

    override fun change(toState: String) {
        dispatch("on-exit")
        state = statesByKey[toState]!!
        dispatch("on-enter")
    }

    override fun publish(key: String, context: OpenContext) {
        val eventContext = context.toEventContext<Character>(this)

        state.publish(key, eventContext)
        subscriber.publish(key, eventContext)
    }

    private fun dispatch(key: String) {
        val eventContext = EventContext<Character>(
            this,
            null,
            game.scene,
            game,
            game.io
        )
        state.publish(key, eventContext)
    }
}
