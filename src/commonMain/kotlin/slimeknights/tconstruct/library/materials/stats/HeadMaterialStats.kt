/*
 * Copyright 2020 KnightSlime
 * Use of this source code is governed by the MIT license that can be found in the license/TConstruct.txt file.
 */
package slimeknights.tconstruct.library.materials.stats

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TranslatedTextComponent
import slimeknights.tconstruct.library.materials.MaterialStatsId

@Serializable
data class HeadMaterialStats(val durability: Int, @SerialName("mining_speed") val miningSpeed: Float, @SerialName("harvest_level") val harvestLevel: Int, val attack: Float) : IMaterialStats {
    companion object {
        val ID = MaterialStatsId("tconstruct", "head")
        val DEFAULT = HeadMaterialStats(1, 1f, 0, 1f)

        const val DURABILITY_LOCALIZATION = "stat.head.durability.name"
        const val MINING_SPEED_LOCALIZATION = "stat.head.mining_speed.name"
        const val ATTACK_LOCALIZATION = "stat.head.attack.name"
        const val HARVEST_LEVEL_LOCALIZATION = "stat.head.harvest_level.name"

        const val DURABILITY_DESCRIPTION_LOCALIZATION = "stat.head.durability.description"
        const val MINING_SPEED_DESCRIPTION_LOCALIZATION = "stat.head.mining_speed.description"
        const val ATTACK_DESCRIPTION_LOCALIZATION = "stat.head.attack.description"
        const val HARVEST_LEVEL_DESCRIPTION_LOCALIZATION = "stat.head.harvest_level.description"

        const val DURABILITY_COLOR = 0xFF47cc47.toInt()
        const val ATTACK_COLOR = 0xFFD76464.toInt()
        const val SPEED_COLOR = 0xFF78A0CD.toInt()
    }

    override val identifier: MaterialStatsId
        get() = ID

    override val descriptions: List<ITextComponent>
        get() = listOf(
            TranslatedTextComponent(DURABILITY_DESCRIPTION_LOCALIZATION),
            TranslatedTextComponent(HARVEST_LEVEL_LOCALIZATION),
            TranslatedTextComponent(MINING_SPEED_LOCALIZATION),
            TranslatedTextComponent(ATTACK_DESCRIPTION_LOCALIZATION)
        )

    override val info: List<ITextComponent>
        get() = listOf(
            IMaterialStats.formatNumber(DURABILITY_LOCALIZATION, DURABILITY_COLOR, durability),
            IMaterialStats.formatNumber(HARVEST_LEVEL_LOCALIZATION, null, harvestLevel),
            IMaterialStats.formatNumber(MINING_SPEED_LOCALIZATION, SPEED_COLOR, miningSpeed),
            IMaterialStats.formatNumber(ATTACK_LOCALIZATION, ATTACK_COLOR, attack)
        )
}