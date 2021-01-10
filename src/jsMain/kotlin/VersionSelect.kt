import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLOptGroupElement
import org.w3c.dom.HTMLOptionElement
import org.w3c.dom.HTMLSelectElement
import org.w3c.dom.get
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.optGroup
import react.dom.option
import react.dom.select

external interface VersionSelectProps : RProps {
    var availableVersions: Map<String, List<String>>
}

@JsExport
class VersionSelectState(val availableVersions: Map<String, List<String>>, val selectedVersion: Pair<String, String>?) : RState

infix fun VersionSelectState.withVersion(newSelectedVersion: Pair<String, String>?) =
    VersionSelectState(availableVersions, newSelectedVersion)

@JsExport
class VersionSelect(props: VersionSelectProps) : RComponent<VersionSelectProps, VersionSelectState>(props) {
    companion object {
        val defaultVersions: Map<String, List<String>> = mapOf(
            "1.12.2" to listOf(
                "vanilla"
            )
        )
    }

    init {
        state = VersionSelectState(props.availableVersions, null)
    }

    override fun RBuilder.render() {
        var version: Pair<String, String>? = state.selectedVersion
        select {
            attrs {
                onChangeFunction = { event ->
                    val select = (event.target as HTMLSelectElement)

                    if (select.selectedOptions.length > 0) {
                        val selected = select.selectedOptions[0] as HTMLOptionElement
                        val group = (selected.parentElement as HTMLOptGroupElement)

                        println("Selected: ${group}/${selected.value}")

                        setState(state withVersion Pair(group.getAttribute("value") ?: group.label, selected.value))
                    }
                }
            }

            state.availableVersions.forEach { (groupName, versions) ->
                optGroup(label = groupName.replace('_', ' ').toLowerCase().capitalize()) {
                    attrs {
                        this["value"] = groupName
                    }

                    versions.forEach { name ->
                        if (version == null) version = Pair(groupName, name)

                        option {
                            attrs {
                                value = name
                                label = name.replace('_', ' ').toLowerCase().capitalize()
                            }
                        }
                    }
                }
            }
        }

        if (version != null) {
            child(ToolSelect::class) {
                attrs {
                    versionName = version!!.first
                    variantName = version!!.second
                }
            }
        }

//        input {
//            attrs {
//                type = InputType.data
//                value = state.name
//                onChangeFunction = { event ->
//                    setState(
//                        VersionSelectState(name = (event.target as HTMLInputElement).value)
//                    )
//                }
//            }
//        }
    }
}