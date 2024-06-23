package tech.alephia.keep.core.scenes

import tech.alephia.keep.core.dialogue.DialogueGraph
import tech.alephia.keep.core.storages.*

fun scene(
    key: String,
    name: String,
    narration: String = "",
    actions: ActionStorage,
    items: ItemStorage = items(),
    characters: CharacterStorage = characters(),
    dialogues: List<DialogueGraph> = emptyList()
) = Scene(key, name, narration, actions, items, characters, dialogues)

fun scenes(vararg scenes: Scene) = scenes.toList()
