package tech.alephia.keep.core.scenes

import tech.alephia.keep.core.actions.Action
import tech.alephia.keep.core.dialogue.DialogueTree
import tech.alephia.keep.core.entities.characters.Character
import tech.alephia.keep.core.entities.items.Item

fun actions(vararg actions: Action) = actions.toList()

fun items(vararg items: Item): List<Item> = items.toList()

fun characters(vararg characters: Character) = characters.toList()

fun dialogues(vararg dialogue: DialogueTree): List<DialogueTree> = dialogue.toList()
