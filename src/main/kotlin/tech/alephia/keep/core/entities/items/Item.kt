package tech.alephia.keep.core.entities.items

import tech.alephia.keep.core.entities.Entity
import tech.alephia.keep.core.entities.items.IndefiniteArticle.Many
import tech.alephia.keep.core.entities.items.IndefiniteArticle.Some
import tech.alephia.keep.core.events.Subscribable

interface Item: Subscribable<Item>, Entity {
    val indefiniteArticle: IndefiniteArticle
    val canBeTaken: Boolean

    fun change(toState: String)

    fun isPlural() = when(indefiniteArticle) {
        Some -> true
        Many -> true
        else -> false
    }

    override fun showAsDefinite(): String = "the $name"
    override fun showAsIndefinite(): String = "$indefiniteArticle $name"
}
