package net.minecraft.util.text

data class Style(
    val color: Int? = null,
    val bold: Boolean? = null,
    val italic: Boolean? = null,
    val underlined: Boolean? = null,
    val strikethrough: Boolean? = null,
    val obfuscated: Boolean? = null,
    val fontID: String? = null
) {
    companion object {
        val EMPTY = Style()
    }
}