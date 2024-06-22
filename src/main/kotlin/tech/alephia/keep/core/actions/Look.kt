package tech.alephia.keep.core.actions

import tech.alephia.keep.core.events.OpenContext

class Look : Action {
    override val key = "look"
    override val name = "Look at"
    override val description = "Look at something"

    override fun run(context: OpenContext) {
        val entity = context.scene.readAnything() ?: return
        context.io.apply {
            finish()
            paragraph(entity.description)
            entity.publish(key, context)
            promptContinue()
        }
    }
}

