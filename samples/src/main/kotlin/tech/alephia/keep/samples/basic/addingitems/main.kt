package tech.alephia.keep.samples.basic.addingitems

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.actions.Leave
import tech.alephia.keep.core.actions.Take
import tech.alephia.keep.core.entities.characters.mainCharacter
import tech.alephia.keep.core.entities.items.item
import tech.alephia.keep.core.scenes.Scene
import tech.alephia.keep.core.scenes.scene
import tech.alephia.keep.core.storages.actions
import tech.alephia.keep.core.storages.items
import tech.alephia.keep.delivery.InOut

fun main() {
    val mainCharacter = mainCharacter("player", "you")

    val items = items(
        item("box", "box", "A heavy empty box."),
        item("stick", "stick", "A stick.", canBeTaken = true)
    )

    val actions = actions(Take(), Leave())
    val welcome = scene("hello-keep", "Hello Keep", "Keep is a text game engine.", actions, items)

    val scenes = listOf(welcome)
    val game = Game(mainCharacter, scenes, "hello-keep")

    game.start()

    while (true) {
        game.draw()
    }
}
