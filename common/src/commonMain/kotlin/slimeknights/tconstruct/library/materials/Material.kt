package slimeknights.tconstruct.library.materials

data class Material(override val identifier: MaterialId, override val isCraftable: Boolean, override val textColour: String, override val temperature: Int) : IMaterial