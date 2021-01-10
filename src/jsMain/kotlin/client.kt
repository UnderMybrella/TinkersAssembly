import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.http.*
import kotlinx.browser.document
import kotlinx.browser.window
import react.dom.render

val http = HttpClient() {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }

    defaultRequest {
        val location = window.location

        url {
            protocol = URLProtocol.createOrDefault(location.protocol.trimEnd(':'))
            host = location.hostname
            location.port.toIntOrNull()?.let(this::port::set)
        }
    }
}

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            child(VersionSelect::class) {
                attrs {
                    availableVersions = VersionSelect.defaultVersions
                }
            }
        }
    }
}
