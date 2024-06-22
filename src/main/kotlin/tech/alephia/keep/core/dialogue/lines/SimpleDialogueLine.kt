package tech.alephia.keep.core.dialogue.lines

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.dialogue.DialogueContext
import tech.alephia.keep.delivery.InOut

class SimpleDialogueLine(
    override val speaker: String,
    private val line: String,
    private val action: (DialogueContext.() -> Unit)? = null
): DialogueLine {
    private lateinit var io: InOut

    override fun setup(game: Game) {
        this.io = game.io
    }

    override fun show(context: DialogueContext) {
        io.apply {
            dialogue(speaker, line)
            action?.invoke(context)
            promptContinue()
        }
    }

    fun withAction(action: DialogueContext.() -> Unit) = SimpleDialogueLine(speaker, line, action)
}
