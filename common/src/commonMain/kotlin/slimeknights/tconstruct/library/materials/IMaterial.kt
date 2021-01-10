/*
 * Copyright 2020 KnightSlime
 * Use of this source code is governed by the MIT license that can be found in the license/TConstruct.txt file.
 */

package slimeknights.tconstruct.library.materials

interface IMaterial {
    companion object {
        /**
         * Fallback material. Used for operations where a material or specific aspects of a material are used,
         * but the given input is missing or does not match the requirements.
         * Think of this as "anti-crash" when trying to build invalid tools.
         * <p>
         * The fallback material needs to have all part types associated with it.
         */
//          IMaterial UNKNOWN = new Material(new ResourceLocation(TConstruct.modID, "unknown"), Fluids.EMPTY, false);
    }

    /**
     * Used to identify the material in NBT and other constructs.
     * Basically everywhere where the material has to be referenced and persisted.
     */
    val identifier: MaterialId

    /**
     * If the material can be crafted into items in the part builder.
     *
     * @return Return false if the material can only be cast or is not craftable at all.
     */
    val isCraftable: Boolean

    /**
     * The fluid associated with this material, if not Fluids.EMPTY.
     * Prerequisite for parts to be cast using the casting table and a cast.
     * Just to make this completely clear: This is the indicator if a material is castable.
     *
     * @return The associated fluid or Fluids.EMPTY if material is not castable
     */
//    fun getFluid(): Fluid?

    /**
     * Gets the translation key for this material
     * @return the translation key
     */
//    fun getTranslationKey(): String? {
//        return Util.makeTranslationKey("material", getIdentifier())
//    }

    /**
     * Gets the text color for this material
     * @return the text color
     */
    val textColour: String

    /**
     * Gets the temperature of this material for use in melting and casting recipes.
     * If this is not castable or meltable, will be 0;
     * @return  Temperature of the material, 0 if not relevant
     */
    val temperature: Int

    /**
     * Gets the encoded text color for this material
     * @return the encoded text color
     */
    val colour: Int
        get() = textColour.toIntOrNull(16)?.let { if (it and 0xFF000000.toInt() == 0) it or 0xFF000000.toInt() else it } ?: 0xFFFFFFFF.toInt()
}