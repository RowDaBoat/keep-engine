package tech.alephia.keep.samples.hellokeep

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.entities.characters.mainCharacter
import tech.alephia.keep.core.scenes.Scene
import tech.alephia.keep.delivery.InOut
import kotlin.reflect.KType

fun main() {
    val inOut = InOut()
    val mainCharacter = mainCharacter("player", "you")
    val welcome = Scene(
        "hello-keep",
        "Hello Keep",
        "Keep is a text-based game engine for adventure games.",
        emptyList()
    )
    val scenes = listOf(welcome)
    val game = Game(inOut, mainCharacter, scenes, "hello-keep")

    game.start()

    while (true) {
        game.draw()
    }
}
