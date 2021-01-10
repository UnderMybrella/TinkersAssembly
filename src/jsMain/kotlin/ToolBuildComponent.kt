import io.ktor.client.request.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLOptionElement
import org.w3c.dom.HTMLSelectElement
import org.w3c.dom.get
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.br
import react.dom.div
import react.dom.label
import react.dom.li
import react.dom.optGroup
import react.dom.option
import react.dom.select
import react.dom.ul
import slimeknights.tconstruct.library.materials.MaterialStatsId
import slimeknights.tconstruct.library.materials.stats.MaterialStats
import slimeknights.tconstruct.library.tools.ToolDefinition
import slimeknights.tconstruct.tools.ToolStatsBuilder

external interface ToolBuilderProps : RProps {
    var versionName: String
    var variantName: String
    var toolName: String
}

@JsExport
class ToolBuilderState(
    val versionName: String,
    val variantName: String,
    val toolName: String,
    val toolMaterials: Array<String>?
) : RState {

}

infix fun ToolBuilderState.withMaterials(materials: Array<String>) =
    ToolBuilderState(versionName, variantName, toolName, materials)

fun ToolBuilderState.withMaterial(index: Int, material: String) =
    ToolBuilderState(versionName, variantName, toolName, toolMaterials?.copyOf()?.also { it[index] = material })

@JsExport
class ToolBuilder(props: ToolBuilderProps) : RComponent<ToolBuilderProps, ToolBuilderState>(props) {
    companion object {
        val materialMap: MutableMap<String, Map<String, MaterialStats>> = HashMap()
    }

    init {
        state = ToolBuilderState(props.versionName, props.variantName, props.toolName, null)
    }

    override fun RBuilder.render() {
        val toolsUrl = "/static/tools/${state.versionName}/${state.variantName}.json"
        if (toolsUrl !in ToolSelect.toolsMap) {
            GlobalScope.launch {
                ToolSelect.toolsMap[toolsUrl] = http.get(toolsUrl)

                setState(state)
            }

            return
        }

        val materialsUrl = "/static/materials/${state.versionName}/${state.variantName}.json"
        if (materialsUrl !in materialMap) {
            GlobalScope.launch {
                materialMap[materialsUrl] = http.get(materialsUrl)

                setState(state)
            }

            return
        }

        val tools = ToolSelect.toolsMap.getValue(toolsUrl)
        val tool = tools[state.toolName]
        if (tool == null) {
            +"Err: No tool by name ${state.toolName} in tools: ${tools.keys.joinToString()}"
            return
        }

        val materials = materialMap.getValue(materialsUrl)

        div {
            +state.toolName.replace('_', ' ').toLowerCase().capitalize()

            if (state.toolMaterials == null) {
                setState({ state -> state withMaterials Array(tool.requiredComponents.size) { materials.keys.first() } })
                return
            }

            br { }

            ul {
                tool.requiredComponents.forEachIndexed { index, component ->
                    li {
                        +"${component.path.replace('_', ' ').toLowerCase().capitalize()}: "
                        select {
                            attrs {
                                onChangeFunction = { event ->
                                    val select = (event.target as HTMLSelectElement)

                                    if (select.selectedOptions.length > 0) {
                                        val selected = select.selectedOptions[0] as HTMLOptionElement

                                        println("New Material @ $index: ${selected.value}")

                                        setState(state.withMaterial(index, selected.value))
                                    }
                                }
                            }

                            val currentMaterial = state.toolMaterials!![index]

                            optGroup {
                                attrs {
                                    label = component.path.replace('_', ' ').toLowerCase().capitalize()
                                }

                                materials.keys.forEach { material ->
                                    option {
                                        attrs {
                                            value = material
                                            label = material.replace('_', ' ').toLowerCase().capitalize()

                                            selected = currentMaterial == material
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            br { }

            +"Materials: ${state.toolMaterials?.joinToString()}"

            val toolMaterials = tool.componentTypes.mapIndexed { index, id -> id to (state.toolMaterials?.get(index)?.let(materials::get) ?: MaterialStats()) }
            val toolBuilder = ToolStatsBuilder.from(toolMaterials)

            br {}

            +buildString {
                append("Durability: ")
                appendLine(toolBuilder.buildDurability())
                appendLine()

                append("Mining Speed: ")
                appendLine(toolBuilder.buildMiningSpeed())
                appendLine()

                append("Harvest Level: ")
                appendLine(toolBuilder.buildHarvestLevel())
                appendLine()

                append("Attack: ")
                appendLine(toolBuilder.buildAttack())
                appendLine()
            }
        }
    }
}
