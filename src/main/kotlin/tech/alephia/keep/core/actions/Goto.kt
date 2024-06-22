package tech.alephia.keep.core.actions

import tech.alephia.keep.core.events.OpenContext

class Goto(
    private val target: String,
    override val description: String
) : Action {
    override val key = "goto:$target"
    override val name = description

    override fun run(context: OpenContext) {
        context.game.changeScene(target)
        context.io.apply {
            finish()
            showContinue()
            waitInput()
        }
    }
}
