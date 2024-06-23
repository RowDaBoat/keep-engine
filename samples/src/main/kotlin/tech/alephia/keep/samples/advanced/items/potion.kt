package tech.alephia.keep.samples.advanced.items

import tech.alephia.keep.core.entities.items.item
import tech.alephia.keep.core.events.onTake
import tech.alephia.keep.core.events.onUse

val potion =
    item(
        "potion",
        "Potion",
        "A potion with magical powers.",
        canBeTaken = true
    ) onUse {
        io.paragraph("You feel magical and refreshed.")
    } onTake {
        io.paragraph("You feel the potion's magic in your fingertips.")
    }
