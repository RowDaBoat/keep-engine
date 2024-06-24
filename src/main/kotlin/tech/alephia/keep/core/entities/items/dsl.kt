package tech.alephia.keep.core.entities.items

import tech.alephia.keep.core.events.EventContext
import tech.alephia.keep.core.events.Subscriptions

fun item(
    key: String,
    name: String,
    description: String,
    indefiniteArticle: IndefiniteArticle = IndefiniteArticle.A,
    canBeTaken: Boolean = false
): Item {
    val state = singleState(name, description, indefiniteArticle, canBeTaken)
    return SimpleItem(key, state.key, listOf(state), defaultSubscriptions())
}

fun item(key: String, initialState: String, vararg states: ItemState): Item =
    SimpleItem(key, initialState, states.toList(), emptySubscriptions())

fun itemState(
    key: String,
    name: String,
    description: String,
    indefiniteArticle : IndefiniteArticle = IndefiniteArticle.A,
    canBeTaken: Boolean = false
) = ItemState(key, name, description, indefiniteArticle, canBeTaken, defaultSubscriptions())

private fun singleState(
    name: String,
    description: String,
    indefiniteArticle: IndefiniteArticle,
    canBeTaken: Boolean
) =
    ItemState("single-state", name, description, indefiniteArticle, canBeTaken, emptySubscriptions())

private fun emptySubscriptions() =
    Subscriptions<Item>()

private fun defaultSubscriptions() =
    Subscriptions(mapOf(Pair("use", EventContext<Item>::notUsable)))

private fun EventContext<Item>.notUsable() {
    io.apply {
        paragraph("${target.showAsDefinite()} cannot be used.")
        showContinue()
        waitInput()
    }
}
