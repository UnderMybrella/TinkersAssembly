package net.minecraft.util.text

data class TranslatedTextComponent(val translationKey: String, override val style: Style = Style.EMPTY, override val siblings: MutableList<ITextComponent> = mutableListOf()) : ITextComponent {
    override val unformattedComponentText: String
        get() = translate(translationKey)
}

/** expect */ fun translate(key: String): String {
    return key
}