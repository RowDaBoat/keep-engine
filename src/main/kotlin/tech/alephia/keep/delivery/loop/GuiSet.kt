package tech.alephia.keep.delivery.loop

class GuiSet {
    private val guis : MutableMap<String, Gui> = mutableMapOf()

    fun add(key: String, gui: Gui) {
        guis[key] = gui
    }

    fun draw() {
        guis.values.forEach { it.draw() }
    }
}
