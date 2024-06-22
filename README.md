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

Then create a scene, it requires a key (`"hello-keep""`) to identify it, and a title name to display (`"Hello Keep""`), scenes usually also have a `narration` as third argument to provide context to the player. The last argument is very important, but we won't use it for now so we just use an empty list.

```kotlin
val welcome = Scene(
    "hello-keep",
    "Hello Keep",
    "Keep is a text-based game engine for adventure games.",
    emptyList()
)
```

Then create a game using the previously declared objects. The last argument is the `key` of the initial scene.

```kotlin
val scenes = listOf(welcome)
val game = Game(inOut, mainCharacter, scenes, "hello-keep")
```

Finally, start the game:

```kotlin
game.start()

while (true) {
    game.draw()
}
```

## Adding actions

