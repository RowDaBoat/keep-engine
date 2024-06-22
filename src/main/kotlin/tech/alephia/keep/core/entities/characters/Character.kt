package tech.alephia.keep.core.entities.characters

import tech.alephia.keep.core.entities.Entity
import tech.alephia.keep.core.events.Subscribable
import tech.alephia.keep.core.storages.ItemStorage

interface Character: Entity, Subscribable<Character> {
    val inventory: ItemStorage

    fun change(toState: String)
}
