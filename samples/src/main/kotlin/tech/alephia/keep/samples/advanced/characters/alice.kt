package tech.alephia.keep.samples.advanced.characters

import tech.alephia.keep.core.entities.characters.npc
import tech.alephia.keep.core.events.onTalk

val alice =
    npc(
        "alice",
        "Alice",
        "Alice, another NPC."
    ) onTalk {
        io.paragraph("${target.name}: Hi, how have you been?")
        io.promptContinue()
    }
