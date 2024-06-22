package tech.alephia.keep.samples.changingscenes

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.actions.Goto
import tech.alephia.keep.core.entities.characters.mainCharacter
import tech.alephia.keep.core.scenes.Scene
import tech.alephia.keep.core.scenes.actions
import tech.alephia.keep.delivery.InOut

fun main() {
    val inOut = InOut()
    val mainCharacter = mainCharacter("player", "you")

    val welcomeActions = actions(Goto("big-room", "Go to the big room."))
    val welcome = Scene("hello-keep", "Hello Keep", "Keep is a text game engine.", welcomeActions)

    val roomActions = actions(Goto("hello-keep", "Go back."))
    val room = Scene("big-room", "Big Room", "You are on a big room.", roomActions)

    val scenes = listOf(welcome, room)
    val game = Game(inOut, mainCharacter, scenes, "hello-keep")

    game.start()

    while (true) {
        game.draw()
    }
}
