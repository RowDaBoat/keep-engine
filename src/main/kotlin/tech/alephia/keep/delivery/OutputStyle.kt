package tech.alephia.keep.delivery

import tech.alephia.keep.delivery.textsprite.Slices
import tech.alephia.keep.delivery.textsprite.sliceWith

val defaultSlicedFrame = """
        .---.
        |   |
        '---'
    """.trimIndent().sliceWith(1, 1, 1, 1)

class OutputStyle(
    val mainTitle: String = "",
    var promptMessage: String = "",
    val promptSymbol: String = "> ",
    val slicedFrame: Slices = defaultSlicedFrame,
    val continueMessage: String = "Continue...",
    val separator: String = "########################################",
    val bugTitle: String = "Bug"
)
