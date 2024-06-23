# Keep, a Text-based Game Engine

[![Test and Publish](https://github.com/RowDaBoat/keep-engine/actions/workflows/ci.yml/badge.svg)](https://github.com/RowDaBoat/keep-engine/actions/workflows/ci.yml)

**Keep** is a game engine for text based adventure games written in Kotlin. It features an easy to read and write domain specific language to design scenes, items, characters, actions, and state machines.

## Index

- Introduction
  - [Getting Started](doc/getting-started.md)
  - [Items](doc/items.md)
  - [Characters](doc/characters.md)
  - [Dialogue Graphs](doc/dialogue-graphs.md)
  - [Actions and Events](doc/actions-and-events.md)

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
