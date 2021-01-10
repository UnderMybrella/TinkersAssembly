package slimeknights.tconstruct.library.materials

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = MaterialStatsIdAsString::class)
data class MaterialStatsId(val namespace: String, val path: String) {
    companion object {
        inline operator fun invoke(resourceName: String): MaterialStatsId =
            resourceName.indexOf(':').let { index ->
                MaterialStatsId(if (index == -1) "minecraft" else resourceName.substring(0, index), resourceName.substring(index + 1))
            }
    }
}

object MaterialStatsIdAsString: KSerializer<MaterialStatsId> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("MaterialStatsId", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: MaterialStatsId) =
        encoder.encodeString("${value.namespace}:${value.path}")

    override fun deserialize(decoder: Decoder): MaterialStatsId =
        MaterialStatsId(decoder.decodeString())
}