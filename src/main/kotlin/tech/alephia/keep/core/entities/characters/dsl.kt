package tech.alephia.keep.core.entities.characters

import tech.alephia.keep.core.events.Subscriptions
import tech.alephia.keep.core.storages.ItemStorage

fun mainCharacter(
    key: String,
    name: String,
    description: String = "",
    inventory: ItemStorage = mainCharacterInventory()
) =
    singleState(name, description)
        .let { mainCharacter(key, inventory, it.key, it) }

fun mainCharacter(
    key: String,
    inventory: ItemStorage = mainCharacterInventory(),
    initialState: String,
    vararg states: CharacterState
): Character =
    SimpleCharacter(key, inventory, initialState, states.toList(), Subscriptions())

fun npc(key: String, name: String, description: String = "", inventory: ItemStorage = ItemStorage()) =
    singleState(name, description).let { npc(key, inventory, it.key, it) }

fun npc(
    key: String,
    inventory: ItemStorage = ItemStorage(),
    initialState: String,
    vararg states: CharacterState
): Character =
    SimpleCharacter(key, inventory, initialState, states.toList(), Subscriptions())

private fun mainCharacterInventory() =
    ItemStorage(
        listOf(),
        "You have",
        "You have",
        "Your inventory is empty."
    )

private fun singleState(name: String, description: String) =
    CharacterState("single-state", name, description, Subscriptions())
