/*
 * Copyright 2020 KnightSlime
 * Use of this source code is governed by the MIT license that can be found in the license/TConstruct.txt file.
 */
package slimeknights.tconstruct.tools

import slimeknights.tconstruct.library.materials.MaterialStatsId
import slimeknights.tconstruct.library.materials.stats.ExtraMaterialStats
import slimeknights.tconstruct.library.materials.stats.HandleMaterialStats
import slimeknights.tconstruct.library.materials.stats.HeadMaterialStats
import slimeknights.tconstruct.library.materials.stats.MaterialStats

class ToolStatsBuilder(val heads: List<HeadMaterialStats>, val handles: List<HandleMaterialStats>, val extras: List<ExtraMaterialStats>) {
    companion object {
        inline fun from(materials: List<Pair<MaterialStatsId, MaterialStats>>): ToolStatsBuilder {
            var headCount = 0
            var handleCount = 0
            var extraCount = 0

            materials.forEach { (id, stats) ->
                when (id) {
                    HeadMaterialStats.ID -> headCount += if (stats.head != null) 1 else 0
                    HandleMaterialStats.ID -> handleCount += if (stats.handle != null) 1 else 0
                    ExtraMaterialStats.ID -> extraCount += if (stats.extra != null) 1 else 0
                }
            }

            val heads = arrayOfNulls<HeadMaterialStats>(headCount)
            val handles = arrayOfNulls<HandleMaterialStats>(handleCount)
            val extras = arrayOfNulls<ExtraMaterialStats>(extraCount)

            headCount = 0
            handleCount = 0
            extraCount = 0

            materials.forEach { (id, stats) ->
                when (id) {
                    HeadMaterialStats.ID -> if (stats.head != null) heads[headCount++] = stats.head
                    HandleMaterialStats.ID -> if (stats.handle != null) handles[handleCount++] = stats.handle
                    ExtraMaterialStats.ID -> if (stats.extra != null) extras[extraCount++] = stats.extra
                }
            }

            return ToolStatsBuilder(
                (heads as Array<HeadMaterialStats>).asList(),
                (handles as Array<HandleMaterialStats>).asList(),
                (extras as Array<ExtraMaterialStats>).asList()
            )
        }
    }

    fun buildDurability(): Int {
        val averageHeadDurability = heads.map(HeadMaterialStats::durability).average()
        val averageExtraDurability = extras.map(ExtraMaterialStats::durability).average()
        val averageHandleDurability = handles.map(HandleMaterialStats::durability).average()
        val averageHandleModifier = handles.map(HandleMaterialStats::modifier).average()

        val durability = (averageHeadDurability + averageExtraDurability) * averageHandleModifier + averageHandleDurability

        return durability.toInt().coerceAtLeast(1)
    }

    fun buildMiningSpeed(): Double =
        heads.map(HeadMaterialStats::miningSpeed).average().coerceAtLeast(1.0)

    fun buildHarvestLevel(): Int =
        heads.maxOfOrNull(HeadMaterialStats::harvestLevel) ?: 0

    fun buildAttack(): Double =
        heads.map(HeadMaterialStats::attack).average().coerceAtLeast(1.0)
}