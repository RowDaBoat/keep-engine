package tech.alephia.keep.samples.advanced.characters

import tech.alephia.keep.core.entities.characters.characterState
import tech.alephia.keep.core.entities.characters.npc
import tech.alephia.keep.core.events.onTalk

val alice =
    npc(
        "alice",
        "first-time",
        characterState(
            "first-time",
            "Alice",
            "Alice, another NPC.",
        ) onTalk {
            io.paragraph("${target.name}: Oh, hi ${game.mainCharacter.name}!.")
            target.change("regular")
            io.promptContinue()
        },
        characterState(
            "regular",
            "Alice",
            "Alice, another NPC, she has already greeted you.",
        ) onTalk {
            io.paragraph("${target.name}: Hello.")
            io.promptContinue()
        }
    )
