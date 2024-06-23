package tech.alephia.keep.samples.advanced.scenes

import tech.alephia.keep.core.actions.*
import tech.alephia.keep.core.scenes.*
import tech.alephia.keep.core.storages.actions
import tech.alephia.keep.core.storages.characters
import tech.alephia.keep.core.storages.dialogueGraphs
import tech.alephia.keep.core.storages.items
import tech.alephia.keep.samples.advanced.characters.alice
import tech.alephia.keep.samples.advanced.characters.bob
import tech.alephia.keep.samples.advanced.dialogues.bobDialogue
import tech.alephia.keep.samples.advanced.items.potion
import tech.alephia.keep.samples.advanced.items.switch

val commonActions = actions(
    Take(), Leave(), Look(), Use(), Talk(),
    Goto("room", "Go to the next room")
)

val keepLobby = scene(
    "keep-lobby", "Keep", "Keep is a text game engine.",
    commonActions,
    items(potion, switch),
    characters(bob, alice),
    dialogueGraphs(bobDialogue)
)
