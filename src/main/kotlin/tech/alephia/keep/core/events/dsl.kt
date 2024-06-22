package tech.alephia.keep.core.events

fun<T, R: Subscribable<T>> R.on(key: String, subscriber: EventContext<T>.() -> Unit): R {
    subscribe(key, subscriber)
    return this
}

infix fun<T, R: Subscribable<T>> R.onUse(subscriber: EventContext<T>.() -> Unit): R =
    on("use", subscriber)

infix fun<T, R: Subscribable<T>> R.onTake(subscriber: EventContext<T>.() -> Unit): R =
    on("take", subscriber)

infix fun<T, R: Subscribable<T>> R.onLeave(subscriber: EventContext<T>.() -> Unit): R =
    on("leave", subscriber)

infix fun<T, R: Subscribable<T>> R.onLook(subscriber: EventContext<T>.() -> Unit): R =
    on("look", subscriber)

infix fun<T, R: Subscribable<T>> R.onTalk(subscriber: EventContext<T>.() -> Unit): R =
    on("talk", subscriber)

infix fun<T, R: Subscribable<T>> R.onEnter(subscriber: EventContext<T>.() -> Unit): R =
    on("on-enter", subscriber)

infix fun<T, R: Subscribable<T>> R.onExit(subscriber: EventContext<T>.() -> Unit): R =
    on("on-exit", subscriber)
