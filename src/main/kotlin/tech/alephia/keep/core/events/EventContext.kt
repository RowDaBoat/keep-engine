package tech.alephia.keep.core.events

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.entities.characters.Character
import tech.alephia.keep.core.scenes.Scene
import tech.alephia.keep.delivery.InOut

class EventContext<T>(
    val target: T,
    val user: Character?,
    val scene: Scene,
    val game: Game,
    val io: InOut
)
