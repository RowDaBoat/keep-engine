package tech.alephia.keep.core

import tech.alephia.keep.core.entities.characters.Character
import tech.alephia.keep.core.scenes.Scene
import tech.alephia.keep.delivery.InOut

class Game(
    val mainCharacter: Character,
    scenes: List<Scene>,
    startingScene: String,
    val io: InOut = InOut(),
) {
    private var scenes = createMap(scenes)

    var scene = this.scenes[startingScene]!!
        private set

    fun start() {
        mainCharacter.setup(this)
        io.changePromptMessage(mainCharacter.name)
        scenes.values.forEach { it.setup(this) }
    }

    fun draw() {
        scene.draw(this)
    }

    private fun createMap(scenes: List<Scene>) =
        scenes.associateBy { scene -> scene.key }.toMutableMap()

    fun changeScene(target: String) {
        scenes[target]?.let { this.scene = it }
            ?: io.bug("Scene $target not found")
    }
}
