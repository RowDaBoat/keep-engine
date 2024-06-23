# Keep - Dialogue Graphs

## Simple Dialogues

A `DialogGraph` is declared using `dialogues` and `dialogue`. Declare a `dialogue` with a `key` to identify it and a few lines, then pass it to `Scene` using `dialogueGraphs` to register it:

```kotlin
val introduction = dialogues(
    dialogue(
        "introduction",
        "Bob" say "Keep dialogues are declarative and easy.",
        "Alice" say "Just using a few `say` lines is the simplest form.",
        "John" say "Alright, got it."
    )
)

val scene = scene(key, title, narration, items, characters, dialogueGraphs(introdcution))
```

## Multiple Lines

A single interlocutor can say multiple lines:

```kotlin
dialogue(
    "introduction",
    "Bob" say lines(
        "Multiple lines can be used...",
        "...for suspense,",
        "or to chunk large dialogues."
    )
)
```

## Callbacks on Lines

Dialogues can run callbacks when a line is shown:

```kotlin
val introduction = dialogues(
    dialogue(
        "potion",
        "Bob" say "Alice, do you happen to have a potion at hand?",
        "Alice" say "Yes, take this"
            action { bob.take(potion) }
    )
)
```

## Go To

Dialogue flow can be reused and redirected using the `goto` function

```kotlin
val introduction = dialogues(
    dialogue(
        "intro-1",
        "Alice" say "Hello!"
            action { goto("outro") },
    ),
    dialogue(
        "intro-2",
        "Alice" say "Hi there."
            action { goto("outro") },
    ),
    dialogue(
        "outro",
        "Alice" say "Have a good one."
    )
)
```

## Dialogue Options

A `dialogue` can branch by providing options to the player. Combine `opt between` and `option`s with `goto` allowing complex dialogues to be written:

```kotlin
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
```
