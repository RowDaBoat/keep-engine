package tech.alephia.keep.samples.advanced.scenes

import tech.alephia.keep.core.actions.Goto
import tech.alephia.keep.core.scenes.Scene
import tech.alephia.keep.core.scenes.actions

val room = Scene(
    "room", "Room", "An empty room.",
    actions(Goto("keep-lobby", "Go back"))
)
