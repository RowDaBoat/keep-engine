package tech.alephia.keep.samples.advanced.characters

import tech.alephia.keep.core.entities.characters.npc
import tech.alephia.keep.core.events.onTalk
import tech.alephia.keep.samples.advanced.dialogues.bobDialogue

val bob =
    npc(
        "bob",
        "Bob",
        "Bob, an NPC."
    ) onTalk { bobDialogue.start("how-awesome") }
