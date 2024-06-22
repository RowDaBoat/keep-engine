package tech.alephia.keep.delivery.textsprite


fun String.spriteSize(): Int2 {
    val split = this.split('\n')
    val width = split.maxBy { it.length }.length
    return Int2(width, split.size)
}

fun String.sliceWith(top: Int, bottom: Int, left: Int, right: Int) =
    Slices(this.trimIndent(), top, bottom, left, right)

fun String.spriteOffset(offset: Int2): String {
    val split = this.split('\n')
    val left = (0..< offset.x).joinToString("") { " " }
    val top = (0..< offset.y).map { "" }
    val withOffset = top.plus(split.map { left.plus(it) })

    return withOffset.joinToString("\n")
}

fun String.spritePad(padding: Int): String {
    val split = this.split('\n')
    return pad(split, padding).joinToString("\n")
}

fun String.spriteAdd(other: String): String {
    val thisSplit = this.split('\n')
    val otherSplit = other.split('\n')
    val size = kotlin.math.max(thisSplit.size, otherSplit.size)
    val thisPadded = pad(thisSplit, size)
    val otherPadded = pad(otherSplit, size)

    return thisPadded.zip(otherPadded)
        .joinToString("\n") { it.first .lineAdd(it.second) }
}

private fun pad(spriteString: List<String>, padTo: Int): List<String> {
    val padding = (0..< padTo - spriteString.size).map { "" }
    return spriteString.plus(padding)
}

private fun String.lineAdd(other: String): String {
    val thisPadded = this.fixPaddingTo(other.length)
    val otherPadded = other.fixPaddingTo(this.length)

    return thisPadded.zip(otherPadded)
        .map { if (it.second == ' ') it.first else it.second }
        .joinToString("")
}

private fun String.fixPaddingTo(padToLength: Int) =
    if (length < padToLength) padTo(padToLength - length) else this

fun String.padTo(length: Int) =
    plus((0..< length).joinToString("") { " " })
