package tech.alephia.keep.core.storages

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.entities.characters.Character
import tech.alephia.keep.core.entities.items.Item

class ItemStorage(
    items: List<Item> = listOf(),
    private val singularEnumerationPrefix: String,
    private val pluralEnumerationPrefix: String,
    private val emptyDescription: String
) {
    private val indexedItems = items.toMutableList()
    private val itemsByKey = items.associateBy { it.key }.toMutableMap()
    private lateinit var game: Game

    fun setup(game: Game) {
        this.game = game
        indexedItems.forEach { it.setup(game) }
    }

    operator fun plus(item: Item) =
        ItemStorage(
            indexedItems + item,
            singularEnumerationPrefix,
            pluralEnumerationPrefix,
            emptyDescription
        )

    operator fun plus(characters: ItemStorage) =
        ItemStorage(
            indexedItems + characters.indexedItems,
            singularEnumerationPrefix,
            pluralEnumerationPrefix,
            emptyDescription
        )

    fun store(item: Item) {
        item.setup(game)
        itemsByKey[item.key] = item
        indexedItems.add(item)
    }

    fun take(key: String): Item? {
        val item = itemsByKey.remove(key) ?: return null
        indexedItems.remove(item)
        return item
    }

    fun get(key: String) = itemsByKey[key]

    fun has(key: String) = itemsByKey.containsKey(key)

    fun all(): Sequence<Item> = indexedItems.asSequence()

    fun show(base: Int) =
        when {
            indexedItems.isEmpty() -> emptyDescription
            indexedItems.size == 1 -> showOneItem(base)
            else -> showManyItems(base)
        }

    fun count() = indexedItems.count()

    private fun showOneItem(base: Int): String {
        val singleItem = indexedItems.first()
        val indexedItem = IndexedValue(0, singleItem)
        return "${selectPrefix()} ${showIndexedItem(indexedItem, base)}."
    }

    private fun selectPrefix() =
        if (indexedItems.first().isPlural())
            pluralEnumerationPrefix
        else
            singularEnumerationPrefix

    private fun showManyItems(base: Int): String {
        val itemsWithIndices = indexedItems.withIndex()
        val withoutLast = itemsWithIndices.take(this.indexedItems.size - 1)
        val last = itemsWithIndices.last()
        return "${selectPrefix()} ${getListedItems(withoutLast, base)} and ${showIndexedItem(last, base)}."
    }

    private fun getListedItems(items: List<IndexedValue<Item>>, base: Int) =
        items.joinToString(", ") { showIndexedItem(it, base) }

    private fun showIndexedItem(indexedItem: IndexedValue<Item>, base: Int) =
        "${indexedItem.value.showAsIndefinite()}[${base + indexedItem.index}]"
}
