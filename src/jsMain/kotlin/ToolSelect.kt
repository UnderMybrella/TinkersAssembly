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
import react.dom.optGroup
import react.dom.option
import react.dom.select
import slimeknights.tconstruct.library.materials.stats.MaterialStats
import slimeknights.tconstruct.library.tools.ToolDefinition

external interface ToolSelectProps : RProps {
    var versionName: String
    var variantName: String
}

@JsExport
class ToolSelectState(val versionName: String, val variantName: String, val selectedTool: String?) : RState {
    infix fun withTool(newTool: String?): ToolSelectState =
        ToolSelectState(versionName, variantName, newTool)
}

@JsExport
class ToolSelect(props: ToolSelectProps) : RComponent<ToolSelectProps, ToolSelectState>(props) {
    companion object {
        val toolsMap: MutableMap<String, Map<String, ToolDefinition>> = HashMap()
        val materialMap: MutableMap<String, Map<String, MaterialStats>> = HashMap()
    }

    init {
        state = ToolSelectState(props.versionName, props.variantName, null)
    }

    override fun RBuilder.render() {
        val toolsUrl = "/static/tools/${state.versionName}/${state.variantName}.json"
        if (toolsUrl !in toolsMap) {
            GlobalScope.launch {
                toolsMap[toolsUrl] = http.get(toolsUrl)

                setState(state)
            }

            return
        }

        div {
            +"${state.versionName.replace('_', ' ').toLowerCase().capitalize()} - ${state.variantName.replace('_', ' ').toLowerCase().capitalize()}"
            val tools = toolsMap.getValue(toolsUrl)
            var selectedTool: String? = state.selectedTool

            br { }

            select {
                attrs {
                    onChangeFunction = { event ->
                        val select = (event.target as HTMLSelectElement)

                        if (select.selectedOptions.length > 0) {
                            val selected = select.selectedOptions[0] as HTMLOptionElement

                            println("New Tool: ${selected.value}")

                            setState(state withTool selected.value)
                        }
                    }
                }

                tools.entries.flatMap { entry -> entry.value.categories.map { category -> category to entry.key } }
                    .groupBy({ it.first }, { it.second })
                    .forEach { (category, tools) ->
                        optGroup(label = category.replace('_', ' ').toLowerCase().capitalize()) {
                            tools.forEach { tool ->
                                if (selectedTool == null) selectedTool = tool

                                option {
                                    attrs {
                                        value = tool
                                        label = tool.replace('_', ' ').toLowerCase().capitalize()
                                    }
                                }
                            }
                        }
                    }
            }

            if (selectedTool != null) {
                child(ToolBuilder::class) {
                    attrs {
                        versionName = state.versionName
                        variantName = state.variantName
                        toolName = selectedTool!!
                    }
                }
            }
        }
    }
}
