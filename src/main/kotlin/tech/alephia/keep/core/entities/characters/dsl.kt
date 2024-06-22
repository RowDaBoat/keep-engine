package tech.alephia.keep.core.entities.characters

import tech.alephia.keep.core.entities.items.IndefiniteArticle
import tech.alephia.keep.core.entities.items.ItemState
import tech.alephia.keep.core.entities.items.defaultItemSubscriptions
import tech.alephia.keep.core.events.Subscriptions
import tech.alephia.keep.core.storages.ItemStorage

fun mainCharacter(
    key: String,
    name: String,
    description: String = "",
    inventory: ItemStorage = mainCharacterInventory()
) =
    singleState(name, description)
        .let { mainCharacter(key, it.key, inventory, it) }

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
    inventory: ItemStorage = ItemStorage()
) =
    singleState(name, description)
        .let { npc(key, it.key, inventory, it) }


fun npc(
    key: String,
    initialState: String,
    vararg states: CharacterState
): Character =
    SimpleCharacter(key, ItemStorage(), initialState, states.toList(), Subscriptions())

fun npc(
    key: String,
    initialState: String,
    inventory: ItemStorage = ItemStorage(),
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
