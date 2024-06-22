package tech.alephia.keep.core.dialogue.lines

import tech.alephia.keep.core.dialogue.DialogueContext
import tech.alephia.keep.delivery.InOut

class DialogueOption(val description: String, private val action: DialogueContext.() -> Unit) {
    private lateinit var io: InOut

    fun setup(game: tech.alephia.keep.core.Game) {
        this.io = game.io
    }

    fun choose(speaker: String, context: DialogueContext) {
        io.dialogue(speaker, description)
        context.action()
    }
}
