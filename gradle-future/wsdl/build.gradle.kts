// Custom task to create a ZIP archive with service WSDLs.
task<Zip>("serviceDefinitionsZIP") {
    baseName = "service-definitions"
    destinationDir = buildDir
    from("src/main/wsdl") {
        exclude("ConvertCooking.wsdl")// We'll download this one directly from the Internet.
    }
}

// Gather produced artifacts into a custom configuration.
configurations.create("serviceDefinitions")

// Declare artifacts produced by this project by tying configurations to files or tasks.
artifacts.add("serviceDefinitions", tasks["serviceDefinitionsZIP"])
