# Keep - Characters

## Simple Characters

Simple characters are declared using the `npc` function with a `key` to identify them, a display `name`, and a `description`. Typically, a callback is registered using `onTalk`, which is called when the `Talk` action is used on the character.

The following example shows Bob, who can be talked to by the player and will answer back.

```kotlin
val bob =
    npc(
        "bob",
        "Bob",
        "Bob, an NPC."
    ) onTalk {
        io.paragraph("${target.name}: Hello, ${game.mainCharacter.name}!.")
        io.promptContinue()
    }
```

## Stateful Characters

Characters can also hold state, allowing them to react to the world and the player's actions. Use an overload of the `npc` function, and declare `key`, `initialState` and `states` parameters.

Each state has its own `key`, the state also has `name` and `description` properties that replace the character's ones. Each state can have its own subscriptions to `onTalk` or any other actions, allowing the character to change its dialogues and behavior. Setting the name and description on each state makes it easy to reflect changes in the character.

The following example shows Alice, who greets the player by his name only once.

```kotlin
val alice =
    npc(
        "alice",
        "first-time",
        characterState(
            "first-time",
            "Alice",
            "Alice, another NPC.",
        ) onTalk {
            io.paragraph("${target.name}: Oh, hi, ${game.mainCharacter.name}!.")
            target.change("regular")
            io.promptContinue()
        },
        characterState(
            "regular",
            "Alice",
            "Alice, another NPC, she has already greeted you.",
        ) onTalk {
            io.paragraph("${target.name}: Hello.")
            io.promptContinue()
        }
    )
```
