package tech.alephia.keep.delivery.textsprite

data class Slices(val text: String, val top: Int, val bottom: Int, val left: Int, val right: Int) {
    private val characters = text.split('\n')

    fun frame(contents: String, offset: Int2 = Int2(0, 0), addedFrameSize: Int2 = Int2(0, 0)): String {
        val borderSize = Int2(left + right, top + bottom)
        val frameSize = contents.spriteSize() + borderSize + addedFrameSize
        val offsetContents = contents.spriteOffset(Int2(left, top) + offset)
        return frame(frameSize).spriteAdd(offsetContents)
    }

    fun frame(size: Int2): String {
        val (width, height) = size
        val result = Array(height) { "" }

        for (i in 0 ..< height)
            for (j in 0 ..< width) {
                val other = charFor(i, j, width, height)
                result[i] = result[i].plus(other)
            }

        return result.joinToString("\n")
    }

    private fun charFor(i: Int, j: Int, width: Int, height: Int): Char {
        val ii = indexFor(i, top, bottom, characters.size, height)
        val jj = indexFor(j, left, right, characters[ii].length, width)

        return characters[ii][jj]
    }

    private fun indexFor(i: Int, lowBorder: Int, highBorder: Int, sliceSize: Int, targetSize: Int) =
        when  {
            i < lowBorder -> i
            i < targetSize - highBorder -> lowBorder
            else -> i - targetSize + sliceSize
        }
}
