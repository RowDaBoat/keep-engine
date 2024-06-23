package tech.alephia.keep.core.actions

import tech.alephia.keep.core.events.OpenContext

class SimpleAction(
    override val key: String,
    override val name: String,
    override val description: String,
    private val runAction: OpenContext.() -> Unit
) : Action {
    override fun run(context: OpenContext) = context.runAction()
}
