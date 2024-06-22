package tech.alephia.keep.core.entities

import tech.alephia.keep.core.events.Publisher

interface Entity: Publisher {
    val key: String
    val name: String
    val description: String

    fun setup(game: tech.alephia.keep.core.Game)
    fun showAsDefinite(): String
    fun showAsIndefinite(): String
}
