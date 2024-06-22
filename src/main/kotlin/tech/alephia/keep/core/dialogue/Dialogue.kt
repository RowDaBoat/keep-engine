package tech.alephia.keep.core.dialogue

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.dialogue.lines.DialogueLine

class Dialogue(
    val key: String,
    val lines: List<DialogueLine>,
) {
    fun setup(game: Game) {
        lines.forEach { it.setup(game) }
    }
}

fun dialogue(key: String, vararg dialogues: List<DialogueLine>) =
    Dialogue(key, dialogues.flatMap { it })
