package tech.alephia.keep.samples.advanced.scenes

import tech.alephia.keep.core.actions.Goto
import tech.alephia.keep.core.scenes.Scene
import tech.alephia.keep.core.scenes.actions
import tech.alephia.keep.core.scenes.items
import tech.alephia.keep.samples.advanced.actions.pickLock
import tech.alephia.keep.samples.advanced.items.safe

val room = Scene(
    "room", "Room", "An room with a huge safe.",
    actions(pickLock, Goto("keep-lobby", "Go back")),
    items(safe)
)
