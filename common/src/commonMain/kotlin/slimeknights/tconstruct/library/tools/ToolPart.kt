package slimeknights.tconstruct.library.tools

import kotlinx.serialization.Serializable
import slimeknights.tconstruct.library.materials.MaterialStatsId

@Serializable
data class ToolPart(val statType: MaterialStatsId, val ingotCost: Double) {
    constructor(name: String, ingotCost: Double): this(MaterialStatsId("tconstruct", name), ingotCost)

    companion object {
        val pickHead = ToolPart("pick_head", 2.0)
        val shovelHead = ToolPart("shovel_head", 2.0)
        val axeHead = ToolPart("axe_head", 2.0)
        val broadAxeHead = ToolPart("broad_axe_head", 8.0)
        val swordBlade = ToolPart("sword_blade", 2.0)
        val largeSwordBlade = ToolPart("large_sword_blade", 8.0)
        val hammerHead = ToolPart("hammer_head", 8.0)
        val excavatorHead = ToolPart("excavator_head", 8.0)
        val kamaHead = ToolPart("kama_head", 2.0)
        val scytheHead = ToolPart("scythe_head", 8.0)
        val panHead = ToolPart("pan_head", 3.0)
        val signHead = ToolPart("sign_head", 3.0)

        val toolRod = ToolPart("tool_rod", 1.0)
        val toughToolRod = ToolPart("tough_tool_rod", 3.0)
        val binding = ToolPart("binding", 1.0)
        val tough_binding = ToolPart("tough_binding", 3.0)

        val wideGuard = ToolPart("wide_guard", 1.0)
        val handGuard = ToolPart("hand_guard", 1.0)
        val crossGuard = ToolPart("cross_guard", 1.0)

        val largePlate = ToolPart("large_plate", 8.0)

        val knifeBlade = ToolPart("knife_blade", 1.0)

        val bowLimb = ToolPart("bow_limb", 3.0)
        val bowString = ToolPart("bow_string", 1.0)

        val arrowHead = ToolPart("arrow_head", 2.0)
        val arrowShaft = ToolPart("arrow_shaft", 2.0)
        val fletching = ToolPart("fletching", 2.0)
        val boltCore = ToolPart("bolt_core", 2.0)
    }
}