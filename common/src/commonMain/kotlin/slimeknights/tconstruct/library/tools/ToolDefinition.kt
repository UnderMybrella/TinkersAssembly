package slimeknights.tconstruct.library.tools

import kotlinx.serialization.Serializable
import slimeknights.tconstruct.library.materials.MaterialStatsId

@Serializable
data class ToolDefinition(val baseStatDefinition: ToolBaseStatDefinition, val requiredComponents: List<MaterialStatsId>, val componentTypes: List<MaterialStatsId>, val categories: Set<String>) {
    companion object {
//        val PICKAXE_1_12_2 = ToolDefinition(
//            ToolBaseStatDefinition.PICKAXE_1_12_2,
//            listOf(ToolPart.toolRod, ToolPart.pickHead, ToolPart.binding),
//            setOf(Category.HARVEST)
//        )
//
//        val PICKAXE_1_16 = ToolDefinition(
//            ToolBaseStatDefinition.PICKAXE_1_16,
//            listOf(ToolPart.toolRod, ToolPart.pickHead, ToolPart.binding),
//            setOf(Category.HARVEST)
//        )
//
//        val SHOVEL = ToolDefinition(
//            ToolBaseStatDefinition.SHOVEL,
//            listOf(ToolPart.toolRod, ToolPart.shovelHead, ToolPart.binding),
//            setOf(Category.HARVEST)
//        )
    }
}