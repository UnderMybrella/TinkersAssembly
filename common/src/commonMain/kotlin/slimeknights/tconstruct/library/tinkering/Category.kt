package slimeknights.tconstruct.library.tinkering

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = CategoryAsString::class)
data class Category(val name: String) {
    companion object {
        val WEAPON = Category("weapon")
        val HARVEST = Category("harvest")
        val AOE = Category("aoe")

        val PROJECTILE = Category("projectile")
        val NO_MELEE = Category("no_melee")
        val LAUNCHER = Category("launcher")
    }
}

object CategoryAsString: KSerializer<Category> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Category", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Category = Category(decoder.decodeString())
    override fun serialize(encoder: Encoder, value: Category) = encoder.encodeString(value.name)
}