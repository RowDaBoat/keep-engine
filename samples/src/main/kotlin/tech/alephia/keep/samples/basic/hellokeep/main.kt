package tech.alephia.keep.samples.basic.hellokeep

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.actions.Talk
import tech.alephia.keep.core.actions.Use
import tech.alephia.keep.core.entities.characters.mainCharacter
import tech.alephia.keep.core.entities.characters.npc
import tech.alephia.keep.core.entities.items.item
import tech.alephia.keep.core.events.onTalk
import tech.alephia.keep.core.events.onUse
import tech.alephia.keep.core.scenes.scene
import tech.alephia.keep.core.scenes.scenes
import tech.alephia.keep.core.storages.actions
import tech.alephia.keep.core.storages.characters
import tech.alephia.keep.core.storages.items

fun main() {
    val mainCharacter = mainCharacter("player", "John")

    val bob = npc("bob", "Bob") onTalk {
        io.paragraph("Hello there stranger!")
        io.promptContinue()
    }

    val potion = item("potion", "Magic Potion", "A mysterious magic potion") onUse {
        io.paragraph("You feel the magic going through you.")
    }

    val scene = scene(
        "hello-keep",
        "Hello Keep",
        "Keep is a text game engine.",
        actions(Talk(), Use()),
        items(potion),
        characters(bob)
    )

    val game = Game(mainCharacter, scenes(scene), "hello-keep")

    game.start()

    while (true) {
        game.draw()
    }
}
