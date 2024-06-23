package tech.alephia.keep.samples.advanced.dialogues

import tech.alephia.keep.core.dialogue.*

val bobDialogue = dialogues(
    dialogue(
        "how-awesome",
        "Bob" say "How awesome is keep?",
        "John" opt between(
            option("It's cool.") { goto("i-think-its-amazing") },
            option("It's awesome.") { goto("i-agree") },
            option("It's amazing") { goto("i-agree") }
        )
    ),
    dialogue(
        "i-think-its-amazing",
        "Bob" say "I think its amazing!"
    ),
    dialogue(
        "i-agree",
        "Bob" say "Couldn't agree more!"
    )
)
