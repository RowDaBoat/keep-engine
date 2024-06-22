package tech.alephia.keep.core.dialogue

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.dialogue.lines.DialogueLine
import tech.alephia.keep.delivery.InOut

class DialogueTree(dialogues: List<Dialogue>) {
    private val dialoguesByKey = dialogues.associateBy { it.key }.toMap()
    private val queue = mutableListOf<DialogueLine>()
    private var lastSpeaker: String = ""
    private lateinit var game: Game
    private lateinit var io: InOut

    fun setup(game: Game) {
        this.game = game
        this.io = game.io
        dialoguesByKey.values.forEach { it.setup(game) }
    }

    fun start(key: String) {
        val context = DialogueContext(this, game)
        goto(key)

        while (queue.isNotEmpty()) {
            val line = queue.removeAt(0)

            if (line.speaker != lastSpeaker && lastSpeaker != "")
                io.nextLine()

            lastSpeaker = line.speaker
            line.show(context)
        }
    }

    fun goto(key: String) {
        queue.clear()
        queue.addAll(dialoguesByKey[key]?.lines ?: emptyList())
    }
}

fun dialogueTree(vararg dialogues: Dialogue) = DialogueTree(dialogues.toList())
