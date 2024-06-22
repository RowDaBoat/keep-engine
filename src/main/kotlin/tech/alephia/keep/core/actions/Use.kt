package tech.alephia.keep.core.actions

import tech.alephia.keep.core.events.OpenContext

class Use : Action {
    override val key = "use"
    override val name = "Use"
    override val description = "Use an item"

    override fun run(context: OpenContext) {
        val entity = context.scene.readAnything() ?: return
        context.io.apply {
            finish()
            entity.publish(key, context)
            promptContinue()
        }
    }
}
