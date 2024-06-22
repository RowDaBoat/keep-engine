package tech.alephia.keep.core.storages

import tech.alephia.keep.core.Game
import tech.alephia.keep.core.entities.characters.Character

class CharacterStorage(
    characters: List<Character> = listOf(),
    private val singularEnumerationPostfix: String = "is here",
    private val pluralEnumerationPostfix: String = "are here",
    private val emptyDescription: String = "There is no one around."
) {
    private val indexedCharacters = characters.toMutableList()
    private val charactersByKey = characters.associateBy { it.key }.toMutableMap()
    private lateinit var game: Game

    fun setup(game: Game) {
        this.game = game
        indexedCharacters.forEach { it.setup(game) }
    }

    fun add(character: Character) {
        character.setup(game)
        charactersByKey[character.key] = character
        indexedCharacters.add(character)
    }

    fun take(key: String): Character? {
        val character = charactersByKey.remove(key) ?: return null
        indexedCharacters.remove(character)
        return character
    }

    fun get(key: String) = charactersByKey[key]

    fun has(key: String) = charactersByKey.containsKey(key)

    fun all(): Sequence<Character> = indexedCharacters.asSequence()

    fun show(base: Int) =
        when {
            indexedCharacters.isEmpty() -> emptyDescription
            indexedCharacters.size == 1 -> showOneCharacter(base)
            else -> showManyItems(base)
        }

    fun count() = indexedCharacters.count()

    private fun showOneCharacter(base: Int): String {
        val singleCharacter = indexedCharacters.first()
        val indexedCharacter = IndexedValue(0, singleCharacter)
        return "${showIndexedCharacter(indexedCharacter, base)} $singularEnumerationPostfix."
    }

    private fun showManyItems(base: Int): String {
        val itemsWithIndices = indexedCharacters.withIndex()
        val withoutLast = itemsWithIndices.take(this.indexedCharacters.size - 1)
        val last = itemsWithIndices.last()
        return "${getListedItems(withoutLast, base)} and ${showIndexedCharacter(last, base)} $pluralEnumerationPostfix."
    }

    private fun getListedItems(items: List<IndexedValue<Character>>, base: Int) =
        items.joinToString(", ") { showIndexedCharacter(it, base) }

    private fun showIndexedCharacter(indexedCharacter: IndexedValue<Character>, base: Int) =
        "${indexedCharacter.value.showAsIndefinite()}[${base + indexedCharacter.index}]"
}
