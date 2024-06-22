package tech.alephia.keep.core.dialogue

import tech.alephia.keep.core.dialogue.lines.DialogueOption
import tech.alephia.keep.core.dialogue.lines.OptionsDialogLine
import tech.alephia.keep.core.dialogue.lines.SimpleDialogueLine

fun between(vararg options: DialogueOption) =
    options.toList()

fun lines(vararg lines: String) =
    lines.toList()

infix fun String.opt(between: List<DialogueOption>) =
    listOf(OptionsDialogLine(this, between))

fun option(description: String, action: DialogueContext.() -> Unit) =
    DialogueOption(description, action)

infix fun String.say(line: String) =
    listOf(SimpleDialogueLine(this, line))

infix fun String.say(lines: List<String>) =
    lines.map { SimpleDialogueLine(this, it) }

infix fun List<SimpleDialogueLine>.action(action: DialogueContext.() -> Unit): List<SimpleDialogueLine> {
    val front = take(size - 1)
    val last = last().withAction(action)
    return front + last
}
