package tech.alephia.keep.samples.advanced.characters

import tech.alephia.keep.core.entities.characters.npc
import tech.alephia.keep.core.events.onTalk

val bob =
    npc(
        "bob",
        "Bob",
        "Bob, an NPC."
    ) onTalk {
        io.paragraph("${target.name}: Hello ${game.mainCharacter.name}!.")
        io.promptContinue()
    }
