package slimeknights.tconstruct.library.materials.stats

import kotlinx.serialization.Serializable
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TranslatedTextComponent
import slimeknights.tconstruct.library.materials.MaterialStatsId

@Serializable
data class HandleMaterialStats(val modifier: Float, val durability: Int): IMaterialStats {
    companion object {
        val ID: MaterialStatsId = MaterialStatsId("tconstruct", "handle")
        val DEFAULT = HandleMaterialStats(1f, 0)

        const val MULTIPLIER_LOCALIZATION = "stat.handle.modifier.name"
        const val DURABILITY_LOCALIZATION = "stat.handle.durability.name"

        const val MULTIPLIER_DESCRIPTION_LOCALIZATION = "stat.handle.modifier.description"
        const val DURABILITY_DESCRIPTION_LOCALIZATION = "stat.handle.durability.description"

        const val DURABILITY_COLOR: Int = HeadMaterialStats.DURABILITY_COLOR
        const val MODIFIER_COLOR: Int = 0xFFB9B95A.toInt()
    }

    override val identifier: MaterialStatsId
        get() = ID

    override val info: List<ITextComponent>
        get() = listOf(
            IMaterialStats.formatNumber(MULTIPLIER_LOCALIZATION, MODIFIER_COLOR, modifier),
            IMaterialStats.formatNumber(DURABILITY_LOCALIZATION, DURABILITY_COLOR, durability)
        )

    override val descriptions: List<ITextComponent>
        get() = listOf(
            TranslatedTextComponent(MULTIPLIER_DESCRIPTION_LOCALIZATION),
            TranslatedTextComponent(DURABILITY_DESCRIPTION_LOCALIZATION)
        )
}