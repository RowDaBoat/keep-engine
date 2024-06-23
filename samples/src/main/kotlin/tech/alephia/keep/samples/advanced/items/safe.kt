package tech.alephia.keep.samples.advanced.items

import tech.alephia.keep.core.entities.items.item
import tech.alephia.keep.core.events.on

val safe = item("safe", "Safe", "A huge safe, what is inside it?")
    .on("pick-lock") {
        io.paragraph("You successfully picked the safe's lock...")
        io.promptContinue()
        io.paragraph("It is empty.")
        io.promptContinue()
    }
