package tech.alephia.keep.samples.advanced.items

import tech.alephia.keep.core.entities.items.item
import tech.alephia.keep.core.entities.items.itemState
import tech.alephia.keep.core.events.onUse

val switch =
    item(
        "switch",
        "off",
        itemState(
            "off",
            "switch (off)",
            "An example of an item with state in Keep. The switch is off."
        ) onUse {
            target.change("on")
            io.paragraph("Turn the switch on...")
        },
        itemState(
            "on",
            "switch (on)",
            "An example of an item with state in Keep. The switch is on."
        ) onUse {
            target.change("off")
            io.paragraph("Turn the switch off...")
        }
    )
