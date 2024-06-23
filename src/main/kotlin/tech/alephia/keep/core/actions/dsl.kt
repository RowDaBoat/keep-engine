package tech.alephia.keep.core.actions

import tech.alephia.keep.core.events.OpenContext

fun action(key: String, name: String, description: String = "", runAction: OpenContext.() -> Unit) =
    SimpleAction(key, name, description, runAction)
