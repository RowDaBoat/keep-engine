package tech.alephia.keep.core.dialogue.lines

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.dialogue.DialogueContext

interface DialogueLine {
    val speaker: String
    fun setup(game: Game)
    fun show(context: DialogueContext)
}
