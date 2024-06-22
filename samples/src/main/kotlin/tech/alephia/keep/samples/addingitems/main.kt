package tech.alephia.keep.samples.addingitems

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.actions.Leave
import tech.alephia.keep.core.actions.Take
import tech.alephia.keep.core.entities.characters.mainCharacter
import tech.alephia.keep.core.entities.items.item
import tech.alephia.keep.core.scenes.Scene
import tech.alephia.keep.core.scenes.actions
import tech.alephia.keep.core.scenes.items
import tech.alephia.keep.delivery.InOut

fun main() {
    val inOut = InOut()
    val mainCharacter = mainCharacter("player", "you")

    val items = items(
        item("box", "box", "A heavy empty box."),
        item("stick", "stick", "A stick.", canBeTaken = true)
    )

    val actions = actions(Take(), Leave())
    val welcome = Scene("hello-keep", "Hello Keep", "Keep is a text game engine.", actions, items)

    val scenes = listOf(welcome)
    val game = Game(inOut, mainCharacter, scenes, "hello-keep")

    game.start()

    while (true) {
        game.draw()
    }
}
