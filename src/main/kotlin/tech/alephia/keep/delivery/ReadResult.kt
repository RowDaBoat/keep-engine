package tech.alephia.keep.delivery

open class ReadResult<T>

class Ok<T>(val value: T) : ReadResult<T>()

class NoInput<T> : ReadResult<T>()

class NotAnIndex<T>(val input: String) : ReadResult<T>()

class NotInBounds<T>(val index: Int) : ReadResult<T>()
