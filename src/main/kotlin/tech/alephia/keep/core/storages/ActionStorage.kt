package tech.alephia.keep.core.storages

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.actions.Action
import tech.alephia.keep.delivery.InOut

class ActionStorage(
    actions: List<Action> = listOf(),
    private val emptyDescription: String
) {
    private val indexedActions = actions.toMutableList()
    private val actionsByKey = actions.associateBy { it.key }.toMutableMap()
    private lateinit var io: InOut

    fun setup(game: Game) {
        this.io = game.io
    }

    operator fun plus(action: Action) =
        ActionStorage(indexedActions + action, emptyDescription)

    operator fun plus(actions: ActionStorage) =
        ActionStorage(indexedActions + actions.indexedActions, emptyDescription)

    fun add(action: Action) {
        actionsByKey[action.key] = action
        indexedActions.add(action)
    }

    fun remove(key: String) : Action? {
        val action = actionsByKey.remove(key) ?: return null
        indexedActions.remove(action)
        return action
    }

    fun get(key: String) = actionsByKey[key]

    fun has(key: String) = actionsByKey.containsKey(key)

    fun count() = indexedActions.count()

    fun show() {
        when {
            indexedActions.isEmpty() -> io.paragraph(emptyDescription)
            else -> {
                indexedActions.withIndex().forEach {
                    io.item(it.index + 1, it.value.description)
                }
            }
        }
    }

    fun all(): Sequence<Action> = indexedActions.asSequence()
}
