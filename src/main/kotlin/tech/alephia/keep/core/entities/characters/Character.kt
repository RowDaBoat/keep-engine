package tech.alephia.keep.core.entities.characters

import tech.alephia.keep.core.entities.Entity
import tech.alephia.keep.core.storages.ItemStorage

interface Character: Entity {
    val inventory: ItemStorage

    fun change(toState: String)
}
