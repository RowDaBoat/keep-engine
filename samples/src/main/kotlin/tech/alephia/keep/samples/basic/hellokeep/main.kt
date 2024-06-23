package tech.alephia.keep.samples.basic.hellokeep

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.entities.characters.mainCharacter
import tech.alephia.keep.core.scenes.Scene
import tech.alephia.keep.core.scenes.scene
import tech.alephia.keep.core.storages.actions
import tech.alephia.keep.delivery.InOut
import kotlin.reflect.KType

fun main() {
    val inOut = InOut()
    val mainCharacter = mainCharacter("player", "you")
    val scene = scene(
        "hello-keep",
        "Hello Keep",
        "Keep is a text game engine.",
        actions()
    )
    val scenes = listOf(scene)
    val game = Game(inOut, mainCharacter, scenes, "hello-keep")

    game.start()

    while (true) {
        game.draw()
    }
}
