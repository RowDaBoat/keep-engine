package tech.alephia.keep.samples.advanced.scenes

import tech.alephia.keep.core.actions.*
import tech.alephia.keep.core.scenes.Scene
import tech.alephia.keep.core.scenes.actions
import tech.alephia.keep.core.scenes.characters
import tech.alephia.keep.core.scenes.items
import tech.alephia.keep.samples.advanced.characters.alice
import tech.alephia.keep.samples.advanced.characters.bob
import tech.alephia.keep.samples.advanced.items.potion
import tech.alephia.keep.samples.advanced.items.switch

val commonActions = actions(Take(), Leave(), Look(), Use(), Talk())

val keepLobby = Scene(
    "keep-lobby", "Keep", "Keep is a text game engine.",
    commonActions + Goto("room", "Go to the next room"),
    items(potion, switch),
    characters(alice, bob)
)
