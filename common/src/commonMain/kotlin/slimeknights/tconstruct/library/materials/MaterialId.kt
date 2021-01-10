package slimeknights.tconstruct.library.materials

data class MaterialId(val namespace: String, val path: String) {
    companion object {
        inline operator fun invoke(resourceName: String): MaterialId =
            resourceName.indexOf(':').let { index ->
                MaterialId(if (index == -1) "minecraft" else resourceName.substring(0, index), resourceName.substring(index + 1))
            }
    }
}