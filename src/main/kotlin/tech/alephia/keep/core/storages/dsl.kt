package tech.alephia.keep.core.storages

import tech.alephia.keep.core.actions.Action
import tech.alephia.keep.core.dialogue.DialogueGraph
import tech.alephia.keep.core.entities.characters.Character
import tech.alephia.keep.core.entities.items.Item

fun actions(vararg actions: Action, emptyDescription: String = "There is nothing to do.") =
    ActionStorage(actions.toList(), emptyDescription)

fun items(
    vararg items: Item,
    singularEnumerationPrefix: String = "There is",
    pluralEnumerationPrefix: String = "There are",
    emptyDescription: String = "There is nothing around."
) =
    ItemStorage(items.toList(), singularEnumerationPrefix, pluralEnumerationPrefix, emptyDescription)

fun characters(
    vararg characters: Character,
    singularEnumerationPostfix: String = "is here",
    pluralEnumerationPostfix: String = "are here",
    emptyDescription: String = "There is no one around."
) =
    CharacterStorage(
        characters.toList(),
        singularEnumerationPostfix,
        pluralEnumerationPostfix,
        emptyDescription
    )

fun dialogueGraphs(vararg dialogue: DialogueGraph) =
    dialogue.toList()
