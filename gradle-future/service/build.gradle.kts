import com.benjaminsproule.swagger.gradleplugin.extension.ApiSourceExtension
import com.benjaminsproule.swagger.gradleplugin.extension.InfoExtension
import com.benjaminsproule.swagger.gradleplugin.extension.SwaggerExtension
import com.github.kongchen.swagger.docgen.mavenplugin.ApiSource
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.gradle.api.tasks.bundling.Jar

// Applying plugins from https://plugins.gradle.org.
plugins {
    id("java")
    id("osgi")
    id("jacoco")
    id("io.spring.dependency-management") version "1.0.3.RELEASE" // Interpolation is not supported yet :(
}

apply {
    plugin("com.benjaminsproule.swagger")
}

val springVersion by project
val cxfVersion by project
val jacksonVersion by project
val swaggerVersion by project
val testngVersion by project
val mockitoVersion by project

// Maven BOMs supported thanks to Spring!
configure<DependencyManagementExtension> {
    imports {
        mavenBom("org.springframework:spring-framework-bom:${springVersion}")
    }
}

configure<JavaPluginConvention> {
    // Configuring separate source set for functional tests. No more inclusions / exclusions for different types of tests.
    sourceSets.create("functionalTest") {
        java.srcDir(file("src/functional-test/java"))
        resources.srcDir(file("src/functional-test/resources"))
    }
}

configurations {
    // Extending functional test classpathes from test. Not very ideomatic, but quick.
    // In a real-world scenario functional tests may not "see" application classes or share dependencies with unit test
    // Functional tests can even use different testing framework, like Spock.
    "functionalTestCompile"{
        extendsFrom(configurations.testCompile)
    }
    "functionalTestRuntime"{
        extendsFrom(configurations.testRuntime)
    }
}

dependencies {
    compile(project(":model"))

    compile("org.springframework:spring-context")
    compile("org.apache.cxf:cxf-rt-frontend-jaxrs:${cxfVersion}")
    compile("org.apache.cxf:cxf-rt-frontend-jaxws:${cxfVersion}")

    compile("com.fasterxml.jackson.jaxrs:jackson-jaxrs-json-provider:${jacksonVersion}")

    compile("io.swagger:swagger-annotations:${swaggerVersion}")

    testCompile("org.testng:testng:${testngVersion}")
    testCompile("org.mockito:mockito-core:${mockitoVersion}")

    // Example of functional test-specific dependency
    "functionalTestCompile"("org.apache.httpcomponents:httpclient:4.5.3")
    // functionalTestCompile "org.testng:testng:${testngVersion}" when not extending test classpath, dependencies must be repeated.
}

(tasks["jar"] as Jar).apply {
    // OSGi MANIFEST.MF configuration goes here.
    // Configuration is similar to Maven BND plugin (http://felix.apache
    // .org/documentation/subprojects/apache-felix-maven-bundle-plugin-bnd.html)
    (manifest as OsgiManifest).apply {
        name = "Playgrounds :: Gradle :: Service"
        symbolicName = "by.dev.madhead.playgrounds.gradle.service"
        vendor = "madhead labs"

        instruction("Export-Package", "") // My mother told me not to talk with strangers!
        instruction("Import-Package",
                "com.fasterxml.jackson.jaxrs.json",
                "com.fasterxml.jackson.databind",
                "org.apache.cxf.feature",
                "org.apache.cxf.jaxws",

                "*" // Import all packages referred in the source code. Actually, it's a default value and can be dropped.
        )
    }
}

// Configuring jacocoTestReport task
(tasks["jacocoTestReport"] as JacocoReport).apply {
    reports {
        html.setEnabled(true)
        xml.setEnabled(true)
        csv.setEnabled(true)
    }
}

(tasks["test"] as Test).apply {
    useTestNG()// Enabling TestNG support. Unit testing is not that different from Maven.
}

task<Test>("functionalTest") {
    useTestNG()// Ok, maybe not the best choice for functional tests...

    testClassesDirs = the(JavaPluginConvention::class).sourceSets["functionalTest"].output.classesDirs
    classpath = the(JavaPluginConvention::class).sourceSets["functionalTest"].runtimeClasspath

    (extensions["jacoco"] as JacocoTaskExtension).apply {
        enabled = false // We don't want to track coverage during functional tests
    }
}

// Integrating integration tests in the build process.
tasks["check"].dependsOn(tasks["functionalTest"]) // We may not want to run functional tests as a part of main task tree
tasks["functionalTest"].mustRunAfter(tasks["test"]) // Force ordering. Obviously, unit tests must run before functional tests


// Swagger configuration is the same as in Maven.
// Actually, Gradle's swagger plugin is a wrapper on Maven's swagger plugin and uses the same classes.
configure<SwaggerExtension> {
    apiSource(closureOf<ApiSourceExtension> {
        isSpringmvc = false
        locations = listOf("by.dev.madhead.playgrounds.gradle.service")
        schemes = listOf(
                "http",
                "https"
        )
        host = "example.com:8080"
        basePath = "/api"
        info(closureOf<InfoExtension> {
            title = "Swagger Gradle Plugin Sample"
            version = "v1"
            description = "This is a sample."
        })
        swaggerDirectory = "${buildDir}/swagger"
    })
}
