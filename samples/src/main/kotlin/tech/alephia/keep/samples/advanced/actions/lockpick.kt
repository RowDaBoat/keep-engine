package tech.alephia.keep.samples.advanced.actions

import tech.alephia.keep.core.actions.action

val pickLock = action("pick-lock", "Pick lock", "Pick a lock.") {
    val item = scene.readItemInRoom() ?: return@action
    io.finish()

    if (true /*lock picking always succeeds for now*/) {
        item.publish("pick-lock", this)
    }
}
