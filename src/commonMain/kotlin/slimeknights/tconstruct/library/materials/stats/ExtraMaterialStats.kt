package slimeknights.tconstruct.library.materials.stats

import kotlinx.serialization.Serializable
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TranslatedTextComponent
import slimeknights.tconstruct.library.materials.MaterialStatsId

@Serializable
data class ExtraMaterialStats(val durability: Int): IMaterialStats {
    companion object {
        val ID: MaterialStatsId = MaterialStatsId("tconstruct", "extra")
        val DEFAULT = ExtraMaterialStats(0)

        const val DURABILITY_LOCALIZATION = "stat.extra.durability.name"
        const val DURABILITY_DESCRIPTION_LOCALIZATION = "stat.extra.durability.description"
        const val DURABILITY_COLOR = HeadMaterialStats.DURABILITY_COLOR
    }

    override val identifier: MaterialStatsId
        get() = ID

    override val info: List<ITextComponent>
        get() = listOf(IMaterialStats.formatNumber(DURABILITY_LOCALIZATION, DURABILITY_COLOR, durability))

    override val descriptions: List<ITextComponent>
        get() = listOf(TranslatedTextComponent(DURABILITY_DESCRIPTION_LOCALIZATION))
}
