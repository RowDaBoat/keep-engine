package tech.alephia.keep.samples.advanced.items

import tech.alephia.keep.core.entities.items.item
import tech.alephia.keep.core.events.onUse

val potion =
    item(
        "potion",
        "Potion",
        "A potion with magical powers.",
        canBeTaken = true
    ) onUse {
        io.paragraph("You feel magical and refreshed.")
    }
