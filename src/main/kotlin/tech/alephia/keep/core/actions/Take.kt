package tech.alephia.keep.core.actions

import tech.alephia.keep.core.entities.items.Item
import tech.alephia.keep.core.events.OpenContext
import tech.alephia.keep.core.storages.ItemStorage

class Take : Action {
    override val key = "take"
    override val name = "Take"
    override val description = "Take something"

    override fun run(context: OpenContext) {
        val scene = context.scene
        val inventory = context.user!!.inventory
        val item = scene.readItemInRoom() ?: return
        doTake(item, scene.items, inventory, context)
    }

    private fun doTake(item: Item, sceneItems: ItemStorage, inventory: ItemStorage, context: OpenContext) {
        context.io.finish()

        if (item.canBeTaken) {
            doTakeItem(item, sceneItems, inventory, context)
        } else {
            cannotTakeItem(item, context)
        }
    }

    private fun doTakeItem(item: Item, sceneItems: ItemStorage, inventory: ItemStorage, context: OpenContext) {
        val takenItem = sceneItems.take(item.key)!!
        inventory.store(takenItem)
        item.publish(key, context)
        context.io.promptContinue()
    }

    private fun cannotTakeItem(item: Item, context: OpenContext) {
        context.io.apply {
            paragraph("${item.showAsDefinite()} cannot be taken.")
            promptContinue()
        }
    }
}
