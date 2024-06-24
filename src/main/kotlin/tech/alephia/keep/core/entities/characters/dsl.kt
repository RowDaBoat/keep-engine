package tech.alephia.keep.core.entities.characters

import tech.alephia.keep.core.events.Subscriptions
import tech.alephia.keep.core.storages.ItemStorage
import tech.alephia.keep.core.storages.items

fun mainCharacter(
    key: String,
    name: String,
    description: String = "",
    inventory: ItemStorage = mainCharacterInventory()
): Character {
    val state = singleState(name, description)
    return mainCharacter(key, state.key, inventory, state)
}

fun mainCharacter(
    key: String,
    initialState: String,
    inventory: ItemStorage = mainCharacterInventory(),
    vararg states: CharacterState
): Character =
    SimpleCharacter(key, inventory, initialState, states.toList(), Subscriptions())

fun npc(
    key: String,
    name: String,
    description: String = "",
    inventory: ItemStorage = items()
): Character {
    val state = singleState(name, description)
    return npc(key, state.key, inventory, state)
}


fun npc(
    key: String,
    initialState: String,
    vararg states: CharacterState
): Character =
    SimpleCharacter(key, items(), initialState, states.toList(), Subscriptions())

fun npc(
    key: String,
    initialState: String,
    inventory: ItemStorage = items(),
    vararg states: CharacterState
): Character =
    SimpleCharacter(key, inventory, initialState, states.toList(), Subscriptions())

fun characterState(
    key: String,
    name: String,
    description: String = "",
) = CharacterState(key, name, description, Subscriptions())

private fun mainCharacterInventory() =
    ItemStorage(
        listOf(),
        "You have",
        "You have",
        "Your inventory is empty."
    )

private fun singleState(name: String, description: String) =
    characterState("single-state", name, description)
