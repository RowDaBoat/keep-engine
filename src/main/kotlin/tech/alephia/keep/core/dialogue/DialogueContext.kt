package tech.alephia.keep.core.dialogue

import tech.alephia.keep.core.Game

class DialogueContext(private val tree: DialogueGraph, val game: Game) {
    fun goto(key: String) = tree.goto(key)
}
