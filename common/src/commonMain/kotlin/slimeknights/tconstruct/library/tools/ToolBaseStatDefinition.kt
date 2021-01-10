/*
 * Copyright 2020 KnightSlime
 * Use of this source code is governed by the MIT license that can be found in the license/TConstruct.txt file.
 */
package slimeknights.tconstruct.library.tools

import kotlinx.serialization.Serializable

/**
 * This class defines the innate properties of a tool.
 * Everything before materials are factored in.
 */
@Serializable
data class ToolBaseStatDefinition(
    /**
     * Multiplier applied to the actual mining speed of the tool
     * Internally a hammer and pick have the same speed, but a hammer is 2/3 slower
     */
    val miningSpeedModifier: Float = 1f,

    /**
     * Multiplier for damage from materials. Should be defined per tool.
     */
    val damageModifier: Float,

    /**
     * A fixed damage value where the calculations start to apply diminishing returns.
     * Basically if you'd hit more than that damage with this tool, the damage is gradually reduced depending on how much the cutoff is exceeded.
     * Helps keeping power creep in check.
     * The default is 15, in general this should be sufficient and only needs increasing if it's a stronger weapon.
     * A diamond sword with sharpness V has 15 damage
     */
    val damageCutoff: Float = 15f,

    /**
     * Allows you set the base attack speed, can be changed by modifiers. Equivalent to the vanilla attack speed.
     * 4 is equal to any standard item. Value has to be greater than zero.
     */
    val attackSpeed: Float = 4.0f,

    /**
     * Knockback modifier. Basically this takes the vanilla knockback on hit and modifies it by this factor.
     */
    val knockbackModifier: Float = 1f
) {
    companion object {
        val PICKAXE_1_16 = ToolBaseStatDefinition(damageModifier = 1f)
        val PICKAXE_1_12_2 = ToolBaseStatDefinition(damageModifier = 1f, attackSpeed = 1.2f)
        val HAMMER = ToolBaseStatDefinition(damageModifier = 1.2f, attackSpeed = 0.8f, miningSpeedModifier = 0.4f)

        val SHOVEL = ToolBaseStatDefinition(damageModifier = 0.9f, attackSpeed = 1.0f)
        val EXCAVATOR = ToolBaseStatDefinition(damageModifier = 1.25f, attackSpeed = 0.7f, miningSpeedModifier = 0.28f)

        val AXE = ToolBaseStatDefinition(damageModifier = 1.1f, attackSpeed = 1.1f, knockbackModifier = 1.3f)
        val LUMBER_AXE = ToolBaseStatDefinition(knockbackModifier = 1.5f, attackSpeed = 0.8f, damageModifier = 1.2f, miningSpeedModifier = 0.35f)

        val MATTOCK = ToolBaseStatDefinition(miningSpeedModifier = 0.95f, damageModifier = 0.9f, knockbackModifier = 1.1f, attackSpeed = 0.9f)

        val KAMA = ToolBaseStatDefinition(damageModifier = 1f, attackSpeed = 1.3f)
        val SCYTHE = ToolBaseStatDefinition(damageModifier = 0.75f, attackSpeed = 0.9f)
    }
}