package tech.alephia.keep.samples.addingnpcs

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.actions.Talk
import tech.alephia.keep.core.entities.characters.mainCharacter
import tech.alephia.keep.core.entities.characters.npc
import tech.alephia.keep.core.events.onTalk
import tech.alephia.keep.core.scenes.Scene
import tech.alephia.keep.core.scenes.actions
import tech.alephia.keep.core.scenes.characters
import tech.alephia.keep.delivery.InOut
import kotlin.reflect.KType

fun main() {
    val inOut = InOut()
    val mainCharacter = mainCharacter("player", "John")
    val actions = actions(Talk())

    val characters = characters(
        npc("bob", "Bob", "Bob, an NPC.")
            onTalk {
                io.paragraph("${target.name}: Hello ${game.mainCharacter.name}!.")
                io.promptContinue()
           }
        ,
        npc("alice", "Alice", "Alice, another NPC.")
            onTalk {
                io.paragraph("${target.name}: Hi, how have you been?")
                io.promptContinue()
            }
    )

    val scene = Scene("hello-keep", "Hello Keep", "Keep is a text game engine.",
        actions,
        characters = characters
    )

    val scenes = listOf(scene)
    val game = Game(inOut, mainCharacter, scenes, "hello-keep")

    game.start()

    while (true) {
        game.draw()
    }
}
