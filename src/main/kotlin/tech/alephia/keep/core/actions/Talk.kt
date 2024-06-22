package tech.alephia.keep.core.actions

import tech.alephia.keep.core.events.OpenContext

class Talk : Action {
    override val key: String = "talk"
    override val name: String = "Talk"
    override val description: String = "Talk to someone"

    override fun run(context: OpenContext) {
        val entity = context.scene.readAnything() ?: return
        context.io.apply {
            finish()
            entity.publish(key, context)
        }
    }
}
