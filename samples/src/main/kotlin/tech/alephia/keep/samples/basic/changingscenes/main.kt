package tech.alephia.keep.samples.basic.changingscenes

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.actions.Goto
import tech.alephia.keep.core.entities.characters.mainCharacter
import tech.alephia.keep.core.scenes.Scene
import tech.alephia.keep.core.scenes.scene
import tech.alephia.keep.core.storages.actions
import tech.alephia.keep.delivery.InOut

fun main() {
    val mainCharacter = mainCharacter("player", "you")

    val welcomeActions = actions(Goto("big-room", "Go to the big room."))
    val welcome = scene("hello-keep", "Hello Keep", "Keep is a text game engine.", welcomeActions)

    val roomActions = actions(Goto("hello-keep", "Go back."))
    val room = scene("big-room", "Big Room", "You are on a big room.", roomActions)

    val scenes = listOf(welcome, room)
    val game = Game(mainCharacter, scenes, "hello-keep")

    game.start()

    while (true) {
        game.draw()
    }
}
