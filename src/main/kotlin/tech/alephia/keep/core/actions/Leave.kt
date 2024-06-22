package tech.alephia.keep.core.actions

import tech.alephia.keep.core.entities.items.Item
import tech.alephia.keep.core.events.OpenContext
import tech.alephia.keep.core.storages.ItemStorage

class Leave : Action {
    override val key = "leave"
    override val name = "Leave"
    override val description = "Leave something"

    override fun run(context: OpenContext) {
        val sceneItems = context.scene.items
        val inventory = context.user!!.inventory
        val item = context.scene.readItemInInventory() ?: return
        doLeave(item, sceneItems, inventory, context)
    }

    private fun doLeave(item: Item, sceneItems: ItemStorage, inventory: ItemStorage, context: OpenContext) {
        val leftItem = inventory.take(item.key)!!
        sceneItems.store(leftItem)

        context.io.apply {
            finish()
            item.publish(key, context)
            promptContinue()
        }
    }
}
