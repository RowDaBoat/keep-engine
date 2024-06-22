package tech.alephia.keep.core.entities.items

import tech.alephia.keep.core.events.Subscriptions

fun item(
    key: String,
    name: String,
    description: String,
    indefiniteArticle: IndefiniteArticle = IndefiniteArticle.A,
    canBeTaken: Boolean = false
): Item = SomeItem(key, name, description, indefiniteArticle, canBeTaken, Subscriptions(defaultItemSubscriptions()))

fun item(key: String, initialState: String, vararg states: ItemState): Item =
    StatefulItem(key, initialState, states.toList())

fun itemState(
    key: String,
    name: String,
    description: String,
    indefiniteArticle : IndefiniteArticle = IndefiniteArticle.A,
    canBeTaken: Boolean = false
) = ItemState(key, name, description, indefiniteArticle, canBeTaken, Subscriptions(defaultItemSubscriptions()))
