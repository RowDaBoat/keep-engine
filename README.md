# Keep Engine

[![Test and Publish](https://github.com/RowDaBoat/keep-engine/actions/workflows/ci.yml/badge.svg)](https://github.com/RowDaBoat/keep-engine/actions/workflows/ci.yml)

**Keep Engine** is a game engine for text based adventure games written in Kotlin. It features an easy to read and write domain specific language to design scenes, items, characters, actions, and state machines.

## Setup

Add the JitPack to your repositories and `keep-keep` to your dependencies on your project's `build.gradle.kts`.

```kotlin
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation "com.github.RowDaBoat:keep-engine:1.0"
}
```

## A simple game

To create a simple `Game` you need: an `InOut` object for input and output, a `Character` object for your main character, and a `Scene` for your initial scene.

First create an `InOut` that will handle the game's input and output:

```kotlin
val inOut = InOut()
```

Then create a main character, it requires a key (`"player"`) to identify it, and a name to display (`"you"`):
```kotlin
val mainCharacter = mainCharacter("player", "you")
```

Then create a scene, it requires a key (`"hello-keep""`) to identify it, and a title name to display (`"Hello Keep""`), scenes usually also have a `narration` as third argument to provide context to the player. The last argument is very important, but we won't use it for now so we just use an empty action list using `actions()`.

```kotlin
val scene = Scene("hello-keep", "Hello Keep", "Keep is a text game engine.", actions())
```

Then create a game using the previously declared objects. The last argument is the `key` of the initial scene.

```kotlin
val scenes = listOf(scene)
val game = Game(inOut, mainCharacter, scenes, "hello-keep")
```

Finally, start the game:

```kotlin
game.start()

while (true) {
    game.draw()
}
```

## Changing scenes

Let's add a new `Scene` and actions to go back and forth between the two.

```kotlin

val welcomeActions = actions(Goto("big-room", "Go to the big room."))
val welcome = Scene("hello-keep", "Hello Keep", "Keep is a text-based game.", welcomeActions)

val roomActions = actions(Goto("hello-keep", "Go back."))
val room = Scene("big-room", "Big Room", "You are on a big room.", roomActions)

val scenes = listOf(welcome, room)
val game = Game(inOut, mainCharacter, scenes, "hello-keep")
```

## Adding items

To add items to the scene, just declare them using `items` and `item`.

```kotlin
val items = items(
    item("box", "box", "A heavy empty box."),
    item("stick", "stick", "A stick.", canBeTaken = true)
)

val actions = actions(Take(), Leave())
val room = Scene("big-room", "Big Room", "You are on a big room.", actions, items)
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
            io.paragraph("${target.name}: Hello ${game.mainCharacter.name}!.")
            io.promptContinue()
        }
    ,
    npc("alice", "Alice", "Alice, another NPC.")
        onTalk {
            io.paragraph("${target.name}: Hi, how have you been?")
            io.promptContinue()
        }
    )

val scene = Scene("hello-keep", "Hello Keep", "Keep is a text game engine.",
    actions, characters = characters
)
```

## Where to go from here

The **Keep Engine** can do much more things to help you design your text-based game, such as:

- [Characters with state](doc/characters.md)
- [Items with state](doc/items.md)
- [Dialogue Trees](doc/dialogues.md)
- [Custom Actions](doc/actions.md)
- [Actions and Events](doc/events.md)
