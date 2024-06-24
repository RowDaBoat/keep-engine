package tech.alephia.keep.core.entities.characters

import tech.alephia.keep.core.events.EventContext
import tech.alephia.keep.core.events.OpenContext
import tech.alephia.keep.core.events.Subscribable
import tech.alephia.keep.core.events.Subscriptions

class CharacterState(
    val key: String,
    val name: String,
    val description: String = "",
    private val subscriber: Subscriptions<Character>
): Subscribable<Character> by subscriber {
    private lateinit var character: Character

    fun setup(character: Character) {
        this.character = character
    }

    fun publish(key: String, context: EventContext<Character>) {
        subscriber.publish(key, context)
    }
}
