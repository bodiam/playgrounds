import no.nils.wsdl2java.Wsdl2JavaPluginExtension
import no.nils.wsdl2java.Wsdl2JavaTask
import org.gradle.api.tasks.bundling.Jar

// Applying plugins from https://plugins.gradle.org.
plugins {
    id("java")
    id("osgi")
    id("no.nils.wsdl2java") version "0.10"
}

// Custom configuration to hold WSDL dependencies.
configurations.create("wsdl")

// Project dependencies can be grouped in different configurations.
// For example, 'compile' and 'test' are available after applying Java plugin, but user can create his own.
dependencies {
    // Depending on a specific project's configuration.
    // Remember we put a ZIP there?
    "wsdl"(project(path = ":wsdl", configuration = "serviceDefinitions"))
}

// Another set of custom tasks.
// These one use bundled Ant to make life simpler.
task("createWSDLDirectory") {
    doLast {
        ant.invokeMethod("mkdir", mapOf("dir" to "${buildDir}/wsdl"))
    }
}

task("fetchCookingUnitConvertorWSDL") {
    dependsOn(tasks["createWSDLDirectory"])

    doLast {
        ant.invokeMethod("get", mapOf(
                "src" to "http://www.webservicex.net/ConvertCooking.asmx?WSDL",
                "dest" to "${buildDir}/wsdl/ConvertCooking.wsdl"
        ))
    }
}

task<Copy>("unpackVolumeAndWeightUnitConvertorWSDLs") {
    dependsOn(tasks["createWSDLDirectory"])// Regular task dependency.
    dependsOn(configurations["wsdl"])// Task can also depend on a configuration, i.e. wait while it is fully resolved.

    from(configurations["wsdl"].map {
        // Regular Groovy^W Kotlin magic
        zipTree(it)
    })

    into(file("${buildDir}/wsdl"))
}

// Meta-task for better composition.
task("prepareWSDL") {
    dependsOn(tasks["fetchCookingUnitConvertorWSDL"])
    dependsOn(tasks["unpackVolumeAndWeightUnitConvertorWSDLs"])
}

// Configuring wsdl2java plugin.
// Every plugin may provide it's own configuration block.
configure<Wsdl2JavaPluginExtension> {
    cxfVersion = "3.1.11"
    deleteGeneratedSourcesOnClean = false
}

// Configuring wsdl2java task.
// This is different from plugin configuration.
// `apply` is from Kotlin!
(tasks["wsdl2java"] as Wsdl2JavaTask).apply {
    dependsOn(tasks["prepareWSDL"])
    wsdlDir = file("${buildDir}/wsdl")
    wsdlsToGenerate = arrayListOf(
            arrayListOf(
                    "-p", "by.dev.madhead.playgrounds.gradle.model.weight",
                    "-wsdlLocation", "",
                    "${buildDir}/wsdl/ConvertWeight.wsdl"
            ),
            arrayListOf(
                    "-p", "by.dev.madhead.playgrounds.gradle.model.volume",
                    "-wsdlLocation", "",
                    "${buildDir}/wsdl/ConvertVolume.wsdl"
            ),
            arrayListOf(
                    "-p", "by.dev.madhead.playgrounds.gradle.model.cooking",
                    "-wsdlLocation", "",
                    "${buildDir}/wsdl/ConvertCooking.wsdl"
            )
    )
    generatedWsdlDir = file("${buildDir}/generated")
}

(tasks["jar"] as Jar).apply {
    // OSGi MANIFEST.MF configuration goes here.
    // Configuration is similar to Maven BND plugin (http://felix.apache
    // .org/documentation/subprojects/apache-felix-maven-bundle-plugin-bnd.html)
    (manifest as OsgiManifest).apply {
        name = "Playgrounds :: Gradle :: Model"
        symbolicName = "by.dev.madhead.playgrounds.gradle.model"
        vendor = "madhead labs"

        instruction(
                "Import-Package",
                "*"// Import all packages referred in the source code. Actually, it's a default value and can be dropped.
        )
    }
}
