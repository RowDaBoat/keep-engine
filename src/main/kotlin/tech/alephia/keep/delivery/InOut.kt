package tech.alephia.keep.delivery

import tech.alephia.keep.delivery.textsprite.Int2

class InOut(private val style: OutputStyle = OutputStyle()) {
    private var input = emptySequence<String>()
    private val frames = mutableListOf(0)

    fun mainTitle() =
        stackedPrintln(style.mainTitle)

    fun title(value: String) =
        stackedPrintln(style.slicedFrame.frame(value, Int2(1, 0), Int2(2, 0)))

    fun subtitle(value: String) =
        stackedPrintln("[ ${value.capitalize()} ]")

    fun text(value: String) =
        stackedPrint(value)

    fun nextLine() =
        stackedPrintln()

    fun finish() =
        stackedPrintln("\n")

    fun paragraph(value: String) {
        text(value.capitalize())
        finish()
    }

    fun item(number: Int, item: String) =
        stackedPrintln(" [$number] $item")

    fun showContinue() =
        subtitle(style.continueMessage)

    fun separator() =
        stackedPrintln("\n${style.separator}\n\n")

    fun promptInput() {
        stackedPrint("\n${style.promptMessage}${style.promptSymbol}")
        waitInput()
    }

    fun promptContinue() {
        frame {
            val continuePrompt = "[ ${style.continueMessage} ]"
            print(continuePrompt)
            update(1)
            readln()
        }
    }

    fun bug(value: String) {
        finish()
        title(style.bugTitle)
        paragraph(value)
    }

    fun dialogue(speaker: String, line: String) {
        stackedPrintln("$speaker: $line")
    }

    fun<T> frame(action: InOut.() -> T): T {
        beginFrame()
        val result = action()
        endFrame()
        return result
    }

    fun waitInput() {
        update(1)
        input = readln().trim().split(' ').asSequence()
    }

    fun<T> read(list: List<T>): ReadResult<T> {
        val first = input.firstOrNull() ?: return NoInput()
        val value = first.toIntOrNull() ?: return NotAnIndex(first)
        if (value < 1 || value > list.size) return NotInBounds(value)

        input = input.drop(1)
        return Ok(list[value - 1])
    }

    fun<T> read(sequence: Sequence<T>, base: Int): ReadResult<T> {
        val first = input.firstOrNull() ?: return NoInput()
        val value = first.toIntOrNull() ?: return NotAnIndex(first)
        val baseId = base + 1
        if (value < baseId) return NotInBounds(value)
        val selected = sequence.drop(value - baseId).firstOrNull() ?: return NotInBounds(value)
        input = input.drop(1)
        return Ok(selected)
    }

    fun changePromptMessage(message: String) {
        style.promptMessage = message
    }

    private fun beginFrame() {
        push()
    }

    private fun endFrame() {
        val back = pop()
        for (i in 0 ..< back)
            print("\u001b[1A")

        print("\u001b[0J")
    }

    private fun stackedPrint(string: String) {
        update(countNewlines(string))
        print(string)
    }

    private fun stackedPrintln(string: String = "") {
        update(countNewlines(string) + 1)
        println(string)
    }

    private fun countNewlines(string: String) =
        string.count { it == '\n' }

    private fun push(count: Int = 0) =
        frames.add(count)

    private fun pop() =
        frames.removeAt(frames.size - 1)

    private fun update(newlines: Int) =
        push(pop() + newlines)
}

fun String.capitalize() = take(1).uppercase() + drop(1)
