package tech.alephia.keep.core.entities.characters

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.events.OpenContext
import tech.alephia.keep.core.storages.ItemStorage

class MainCharacter(
    override val key: String,
    override val name: String,
    override val description: String,
    override val inventory: ItemStorage = mainCharacterInventory()
) : Character {
    private lateinit var game: Game

    override fun setup(game: Game) {
        this.game = game
        inventory.setup(game)
    }

    override fun showAsDefinite() = name

    override fun showAsIndefinite() = name

    override fun publish(key: String, context: OpenContext) {
    }

    override fun change(toState: String) {
    }
}

private fun mainCharacterInventory() =
    ItemStorage(
        listOf(),
        "You have",
        "You have",
        "The inventory is empty."
    )
