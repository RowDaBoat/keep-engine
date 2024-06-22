package tech.alephia.keep.delivery.textsprite

data class Int2(var x: Int, var y: Int) {
    operator fun plus(value: Int2) = Int2(x + value.x, y + value.y)

    operator fun minus(value: Int2) = Int2(x - value.x, y - value.y)

    operator fun times(value: Int) = Int2(x * value, y * value)

    operator fun times(value: Int2) = Int2(x * value.x, y * value.y)

    operator fun div(value: Int) = Int2(x / value, y / value)

    operator fun div(value: Int2) = Int2(x / value.x, y / value.y)

    operator fun unaryMinus() = Int2(-x, -y)

    operator fun get(index: Int) = when(index) {
        0 -> x
        1 -> y
        else -> throw Exception("No component on index $index")
    }

    operator fun plusAssign(value: Int2) {
        x += value.x
        y += value.y
    }

    operator fun minusAssign(value: Int2) {
        x -= value.x
        y -= value.y
    }

    operator fun timesAssign(value: Int) {
        x *= value
        y *= value
    }

    operator fun timesAssign(value: Int2) {
        x *= value.x
        y *= value.y
    }

    operator fun divAssign(value: Int) {
        x /= value
        y /= value
    }

    operator fun divAssign(value: Int2) {
        x /= value.x
        y /= value.y
    }

    override operator fun equals(other: Any?) =
        other != null &&
        other is Int2 &&
        x == other.x &&
        y == other.y

    override fun hashCode() = 6113 * x + y

    override fun toString() = "($x, $y)"
}
