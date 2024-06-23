package tech.alephia.keep.core.scenes

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.actions.Action
import tech.alephia.keep.core.dialogue.DialogueGraph
import tech.alephia.keep.core.entities.Entity
import tech.alephia.keep.core.entities.characters.Character
import tech.alephia.keep.core.entities.items.Item
import tech.alephia.keep.core.events.OpenContext
import tech.alephia.keep.core.storages.ActionStorage
import tech.alephia.keep.core.storages.CharacterStorage
import tech.alephia.keep.core.storages.ItemStorage
import tech.alephia.keep.delivery.*

class Scene(
    val key: String,
    val name: String,
    private val narration: String,
    private val actions: ActionStorage,
    val items: ItemStorage,
    val characters: CharacterStorage,
    private val dialogues: List<DialogueGraph> = emptyList()
) {
    private lateinit var io: InOut
    private lateinit var game: Game

    fun setup(game: Game) {
        this.game = game
        io = game.io
        items.setup(game)
        actions.setup(game)
        dialogues.forEach { it.setup(game) }
    }

    fun draw(game: Game) {
        io.apply {
            scene()
            var count = drawRoomItems()
            count += drawInventoryItems(game, count)
            count += drawCharacters(count)
            sceneActions()
            io.promptInput()
        }

        readAction()?.also {
            io.text("${it.name} ")
            runAction(it, game)
        }
    }

    fun readAnything(): Entity? {
        val inventory = game.mainCharacter.inventory
        val entities = items.all() + inventory.all() + characters.all()
        return readAnything(entities, 0)
            ?.also { io.text(it.name) }
    }

    fun readItemInRoom(): Item? {
        val roomItems = items.all()
        return readItems(roomItems, 0)
            ?.also { io.text(it.name) }
    }

    fun readItemInInventory(): Item? {
        val inventoryItems = game.mainCharacter.inventory.all()
        return readItems(inventoryItems, items.count())
            ?.also { io.text(it.name) }
    }

    private fun InOut.scene() {
        separator()
        title(name)
        paragraph(narration)
    }

    private fun InOut.drawRoomItems(): Int {
        subtitle("Room")
        paragraph(items.show(1))
        return items.count() + 1
    }

    private fun InOut.drawInventoryItems(game: Game, base: Int): Int {
        subtitle("Inventory")
        val inventory = game.mainCharacter.inventory
        paragraph(inventory.show(base))
        return base + inventory.count()
    }

    private fun InOut.drawCharacters(base: Int): Int {
        subtitle("Characters")
        paragraph(characters.show(base))
        return base + characters.count()
    }

    private fun InOut.sceneActions() {
        subtitle("Actions")
        actions.show()
    }

    private fun runAction(action: Action, game: Game) {
        action.run(OpenContext(this, game, io, game.mainCharacter))
    }

    private fun readAction(): Action? {
        when(val result = io.read(actions.all(), 0)) {
            is Ok -> return result.value
            is NoInput -> treatError("Choose an action using its [number].")
            is NotAnIndex -> treatError("\"${result.input}\" is not an action [number].")
            is NotInBounds -> treatError("There is no action at [${result.index}]")
        }

        return null
    }

    private fun readItems(items: Sequence<Item>, base: Int): Item? {
        when(val result = io.read(items, base)) {
            is Ok -> return result.value
            is NoInput -> treatError("Choose an item using its [number].")
            is NotAnIndex -> treatError("\"${result.input}\" is not an item [number].")
            is NotInBounds -> treatError("There is no item at [${result.index}]")
        }

        return null
    }

    private fun readAnything(entity: Sequence<Entity>, base: Int): Entity? {
        when(val result = io.read(entity, base)) {
            is Ok -> return result.value
            is NoInput -> treatError("Choose something using its [number].")
            is NotAnIndex -> treatError("\"${result.input}\" is nothing at [number].")
            is NotInBounds -> treatError("There is nothing at [${result.index}]")
        }

        return null
    }

    private fun treatError(noInputMessage: String) {
        io.frame {
            finish()
            paragraph(noInputMessage)
            promptContinue()
        }
    }
}
