package tech.alephia.keep.core.entities.items

import tech.alephia.keep.core.events.EventContext

fun defaultItemSubscriptions() = mapOf(
    Pair("use", EventContext<Item>::notUsable)
)

fun EventContext<Item>.notUsable() {
    io.apply {
        paragraph("${target.showAsDefinite()} cannot be used.")
        showContinue()
        waitInput()
    }
}
