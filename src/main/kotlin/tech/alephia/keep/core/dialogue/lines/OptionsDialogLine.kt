package tech.alephia.keep.core.dialogue.lines

import tech.alephia.keep.core.dialogue.DialogueContext
import tech.alephia.keep.delivery.InOut
import tech.alephia.keep.delivery.Ok
import tech.alephia.keep.delivery.ReadResult
import tech.alephia.keep.delivery.treatInputError

class OptionsDialogLine(
    override val speaker: String,
    private val between: List<DialogueOption>
): DialogueLine {
    private lateinit var io: InOut

    override fun setup(game: tech.alephia.keep.core.Game) {
        this.io = game.io
        between.forEach { it.setup(game) }
    }

    override fun show(context: DialogueContext) {
        val chosen = io.frame {
            showOptions()
            readOption()
        }

        chosen.choose(speaker, context)
        io.promptContinue()
    }

    private fun showOptions() {
        io.apply {
            dialogue(speaker, "")
            between.withIndex().forEach { item(it.index + 1, it.value.description) }
        }
    }

    private fun readOption(): DialogueOption {
        while(true) {
            val result = io.frame { readResult() }

            if (result is Ok)
                return result.value

             treatInputError(io, result, "an", "option")
        }
    }

    private fun readResult(): ReadResult<DialogueOption> {
        io.apply {
            promptInput()
            return read(between)
        }
    }
}
