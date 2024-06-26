# Keep - Items

## Simple Items

The simplest items are declared using the `item` function with a `key` to identify it, a display `name`, and a `description`. Typically, a callback is registered using `onUse`, which is called when the `Use` action is used on the item.

The following example shows a potion that can be used by the player.

```kotlin
val potion =
    item(
        "potion",
        "Potion",
        "A potion with magical powers.",
        canBeTaken = true
    ) onUse {
        io.paragraph("You feel magical and refreshed.")
    }
```

## Stateful Items

Items can hold a state, allowing the world to react and be modified by the player's actions. Use an overload of the `item` function, and declare `key`, `initialState`, and `states` parameters.

Each state has its own `key`, the state also has `name` and `description` properties that replace the item's ones.  Each state can have its own subscriptions to `onUse` or any other actions, allowing the item to specify its behavior.

The following example shows a switch that can be turned on or off by the player.

```kotlin
val switch =
    item(
        "switch",
        "off",
        itemState(
            "off",
            "switch (off)",
            "An example of an item with state in Keep. The switch is off."
        ) onUse {
            target.change("on")
            io.paragraph("You turn the switch on...")
        },
        itemState(
            "on",
            "switch (on)",
            "An example of an item with state in Keep. The switch is on."
        ) onUse {
            target.change("off")
            io.paragraph("You turn the switch off...")
        }
    )
```
