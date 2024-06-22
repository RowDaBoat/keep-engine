package tech.alephia.keep.core.entities.characters

import tech.alephia.keep.core.events.Subscriptions
import tech.alephia.keep.core.storages.ItemStorage

fun npc(key: String, name: String, description: String, inventory: ItemStorage = ItemStorage()) =
    NPC(key, name, description, inventory, Subscriptions())

fun npc(key: String, initialState: String, inventory: ItemStorage = ItemStorage(), vararg states: CharacterState) =
    StatefulNPC(key, inventory, initialState, states.toList(), Subscriptions())
