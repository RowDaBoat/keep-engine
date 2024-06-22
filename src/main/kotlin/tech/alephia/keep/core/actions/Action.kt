package tech.alephia.keep.core.actions

import tech.alephia.keep.core.events.OpenContext

interface Action {
    val key: String
    val name: String
    val description: String

    fun run(context: OpenContext)
}
