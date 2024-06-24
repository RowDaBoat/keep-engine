# Keep, a Text-based Game Engine

[![Test and Publish](https://github.com/RowDaBoat/keep-engine/actions/workflows/ci.yml/badge.svg)](https://github.com/RowDaBoat/keep-engine/actions/workflows/ci.yml)

**Keep** is a game engine for text-based adventure games written in Kotlin. It features an easy to read and write domain specific language to design scenes, items, characters, actions, and state machines.

## Index

- [Getting Started](doc/getting-started.md)
- [Items](doc/items.md)
- [Characters](doc/characters.md)
- [Dialogue Graphs](doc/dialogue-graphs.md)
- [Actions and Events](doc/actions-and-events.md)

## Setup

Add JitPack to your repositories and `keep-engine` to your dependencies on your project's `build.gradle.kts`.

```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.RowDaBoat", "keep-engine", "0.0.1")
}
```

## A simple game

This is a simple game written in **Keep** with a main character, an NPC, an item and a scene.

```kotlin
fun main() {
    val mainCharacter = mainCharacter("player", "John")

    val bob = npc("bob", "Bob") onTalk {
        io.paragraph("Hello there stranger!")
        io.promptContinue()
    }

    val potion = item("potion", "Magic Potion", "A mysterious magic potion") onUse {
        io.paragraph("You feel the magic going through you.")
    }

    val scene = scene(
        "hello-keep",
        "Hello Keep",
        "Keep is a text game engine.",
        actions(Talk(), Use()),
        items(potion),
        characters(bob)
    )

    val game = Game(mainCharacter, scenes(scene), "hello-keep")

    game.start()

    while (true) {
        game.draw()
    }
}
```

The game displays the following when run:
```
.------------.
| Hello Keep |
'------------'
Keep is a text game engine.

[ Room ]
There is a Magic Potion[1].

[ Inventory ]
Your inventory is empty.

[ Characters ]
Bob[2] is here.

[ Actions ]
[1] Talk to someone
[2] Use an item

John> 
```

The player inputs an action number, and then a number for the target if necessary.

ie: the input `1 2` talks[1] to bob[2], and `2 1` uses[2] the potion[1].
