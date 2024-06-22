package tech.alephia.keep.core.entities.items

enum class IndefiniteArticle {
    A, An, Many, Some, NoArticle;

    override fun toString() = super.toString().lowercase()
}
