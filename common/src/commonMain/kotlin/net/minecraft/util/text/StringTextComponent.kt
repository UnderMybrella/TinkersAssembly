package net.minecraft.util.text

data class StringTextComponent(override val unformattedComponentText: String, override val style: Style = Style.EMPTY, override val siblings: MutableList<ITextComponent> = mutableListOf()) : ITextComponent