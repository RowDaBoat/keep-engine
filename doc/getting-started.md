# Keep - Getting Started

## Index
- [Getting Started with a simple game](#getting-Started-with-a-simple-game)
- [Changing Scenes](#changing-scenes)
- [Adding Items](#adding-items)
- [Adding NPCs](#adding-npcs)
- [Where to go from here](#where-to-go-from-here)

## Getting Started with a simple game

To create a `Game` you need at least:
- A `Character` for your main character.
- A `Scene` where the game starts.

Create a main character, it requires:
- A key (`"player"`) to identify it.
- A name to display (`"you"`).

```kotlin
val mainCharacter = mainCharacter("player", "you")
```

Then, create a scene, it requires:
- A key (`"hello-keep"`) to identify it.
- A title to display (`"Hello Keep"`).
- A `narration` to provide context to the player.

The last argument is very important, but we won't use it for now, so we just use `actions()`.

```kotlin
val scene = scene("hello-keep", "Hello Keep", "Keep is a text game engine.", actions())
```

Next, create a game using the previously declared objects. The last argument is the `key` of the initial scene.

```kotlin
val game = Game(mainCharacter, scenes(scene), "hello-keep")
```

Finally, start and run the game with:

```kotlin
game.start()

while (true) {
    game.draw()
}
```

## Changing scenes

Let's add a new `Scene`, and actions to change between them.

```kotlin

val welcomeActions = actions(Goto("big-room", "Go to the big room."))
val welcome = scene("hello-keep", "Hello Keep", "Keep is a text-based game.", welcomeActions)

val roomActions = actions(Goto("hello-keep", "Go back."))
val room = scene("big-room", "Big Room", "You are on a big room.", roomActions)

val scenes = scenes(welcome, room)
val game = Game(inOut, mainCharacter, scenes, "hello-keep")
```

## Adding items

To add items to the scene declare them using `items` and `item`.

```kotlin
val items = items(
    item("box", "box", "A heavy empty box."),
    item("stick", "stick", "A stick.", canBeTaken = true)
)

val actions = actions(Take(), Leave())
val room = scene("big-room", "Big Room", "You are in a big room.", actions, items)
```

## Adding NPCs

To add NPCs to the scene, declare them using `characters` and `npc`.
Using `onTalk` you can register whatever dialogue or custom behavior you want the character to do when talked to.
Don't forget to add the `characters` and `Talk` action to the scene.

```kotlin
val actions = actions(Talk())

val characters = characters(
    npc("bob", "Bob", "Bob, an NPC.")
        onTalk {
            io.paragraph("${target.name}: Hello, ${game.mainCharacter.name}!.")
            io.promptContinue()
        }
    ,
    npc("alice", "Alice", "Alice, another NPC.")
        onTalk {
            io.paragraph("${target.name}: Hi, how have you been?")
            io.promptContinue()
        }
    )

val scene = scene("hello-keep", "Hello Keep", "Keep is a text game engine.",
    actions, characters = characters
)
```

## Where to go from here

**Keep** can do much more to help you design your game, check the [index](../README.md#index) for in-depth documentation on items, characters, dialogue, actions, and events.
