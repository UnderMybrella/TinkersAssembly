/*
 * Copyright 2020 KnightSlime
 * Use of this source code is governed by the MIT license that can be found in the license/TConstruct.txt file.
 */
package slimeknights.tconstruct.library.materials.stats

import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.StringTextComponent
import net.minecraft.util.text.Style
import net.minecraft.util.text.TranslatedTextComponent
import slimeknights.tconstruct.library.materials.MaterialStatsId

/**
 * Basic interface for all material stats.
 * Note that you should extend {@link BaseMaterialStats} for your material to load from the JSONs.
 */
interface IMaterialStats {
    companion object {
        inline fun formatNumber(loc: String, colour: Int?, number: Number): ITextComponent {
            // Exact integral part of d.
            val integralPartAsInt = number.toInt()

            // Exact fractional part of d (since we subtract it's integral part).
            val exactFractionalPart: Double = number.toDouble() - integralPartAsInt.toDouble()

            // Approximated scaled fractional part of d (due to multiplication).
            var scaledFractional: Double = exactFractionalPart * 100.0

            // Exact integral part of scaled fractional above.
            val fractionalPartAsInt = scaledFractional.toInt()

            val hundreds = integralPartAsInt % 1_000
            val thousands = (integralPartAsInt - hundreds) / 1_000 % 1_000
            val million = (integralPartAsInt - hundreds - (thousands * 1_000)) / 1_000_000 % 10

            return TranslatedTextComponent(loc, Style(colour)).apply {
                siblings.add(StringTextComponent(buildString {
                    if (million > 0) {
                        append(million)
                        append(',')
                    }

                    if (million > 0 || thousands > 0) {
                        append(thousands)
                        append(',')
                    }

                    append(hundreds)

                    if (fractionalPartAsInt > 0) {
                        append('.')
                        append(fractionalPartAsInt)
                    }
                }))
            }
        }
    }

    /**
     * Returns a unique ResourceLocation to identify the type of stats the material has.
     */
    val identifier: MaterialStatsId

    /**
     * Returns the name of the stat type, to be displayed to the player.
     */
    val name: ITextComponent
        get() = TranslatedTextComponent("stat.${identifier.path}.name")

    /**
     * Returns a list containing a String for each player-relevant value.
     * Each line should consist of the name of the value followed by the value itself.
     * Example: "Durability: 25"
     *
     * This is used to display properties of materials to the user.
     */
    val info: List<ITextComponent>

    /**
     * Returns a list containing a Text Component describing each player-relevant value.
     * The indices of the lines must line up with the lines from getLocalizedInfo()!
     * *
     * This is used to display properties of materials to the user.
     * @return a list of Text Components
     */
    val descriptions: List<ITextComponent>
}