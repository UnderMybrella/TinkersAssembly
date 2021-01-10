package slimeknights.tconstruct.library.materials.stats

import kotlinx.serialization.Serializable

@Serializable
data class MaterialStats(
    val head: HeadMaterialStats? = null,
    val handle: HandleMaterialStats? = null,
    val extra: ExtraMaterialStats? = null
)