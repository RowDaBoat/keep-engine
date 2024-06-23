# Keep - Actions and Events

## Predefined Actions

In `Keep` actions are used to define the game's behavior when the player interacts with the scenes, the engine has some predefined basic actions:

- `Leave()`: leaves an item from the player's inventory on the scene.
- `Take()`: takes an item from the scene and puts it into the player's inventory.
- `Talk()`: talks to another character in the scene.
- `Look()`: looks at an item in the room or in the player's inventory
- `Goto(target, description)`: changes the current scene for `target` and is displayed as `description` in the "actions" menu.

## Subscribing to Events

Actions can be subscribed on items, characters, and their states to execute custom behavior whenever a subscribed action is called on them.

```kotlin
val potion =
    item(
        "potion",
        "Potion",
        "A potion with magical powers.",
        canBeTaken = true
    ) onUse {
        io.paragraph("You feel magical and refreshed.")
    } onTake {
        io.paragraph("You feel the potion's magic in your fingertips.")
    }
```

There is a shorthand `on` method for each of the predefined actions' events, each one is equivalent to calling `on(eventKey)` with the key corresponding to each event.
You can use `on(eventKey)` to subscribe to events from your own custom actions.

## Custom Actions

To create an action with custom behavior, you can use the `action` function:

```kotlin

val lockpick = action("lockpick", "Lockpick", "Pick a lock.") {
    val item = scene.readItemInRoom() ?: return@action
    io.finish()

    if (true /*lockpicking always succeeds for now*/) {
        item.publish("lockpick", this)
    }
}

val safe = item("safe", "Safe", "A huge safe, what is inside it?")
    .on("lockpick") {
        io.paragraph("You successfully lockpicked the safe...")
        io.promptContinue()
        io.paragraph("It is empty.")
        io.promptContinue()
    }
```

You can also implement the `Action` interface if you need a more complex behavior.
