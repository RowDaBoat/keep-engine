package tech.alephia.keep.samples.advanced

import tech.alephia.keep.core.Game
import tech.alephia.keep.delivery.InOut
import tech.alephia.keep.samples.advanced.characters.john
import tech.alephia.keep.samples.advanced.scenes.keepLobby
import tech.alephia.keep.samples.advanced.scenes.room

fun main() {
    val inOut = InOut()

    val scenes = listOf(keepLobby, room)
    val game = Game(inOut, john, scenes, "keep-lobby")

    game.start()

    while (true) {
        game.draw()
    }
}
