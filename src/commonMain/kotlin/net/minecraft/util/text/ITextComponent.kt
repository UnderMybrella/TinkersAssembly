package net.minecraft.util.text

interface ITextComponent {
    val style: Style
    val unformattedComponentText: String

    val siblings: MutableList<ITextComponent>
}