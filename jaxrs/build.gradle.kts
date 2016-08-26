buildscript {
    repositories {
        jcenter()
        gradleScriptKotlin()
    }

    dependencies {
        classpath(kotlinModule(module = "gradle-plugin"))
        classpath("com.bmuschko:gradle-tomcat-plugin:2.2.5")
    }
}

val kotlinVersion by project
val jaxrsVersion by project
val jerseyVersion by project
val servletVersion by project
val tomcatVersion by project

subprojects { subproject ->
    with(subproject.plugins) {
        apply("kotlin")
        apply("com.bmuschko.tomcat")
    }

    subproject.repositories {
        jcenter()
    }

    subproject.dependencies {
        compile(kotlinModule(module = "stdlib", version = "${kotlinVersion}"))
        compile("javax.ws.rs:javax.ws.rs-api:${jaxrsVersion}")
        compile("org.glassfish.jersey.bundles:jaxrs-ri:${jerseyVersion}")
        compileOnly("javax.servlet:javax.servlet-api:${servletVersion}")
        "tomcat"("org.apache.tomcat.embed:tomcat-embed-core:${tomcatVersion}")
        "tomcat"("org.apache.tomcat.embed:tomcat-embed-logging-juli:${tomcatVersion}")
        "tomcat"("org.apache.tomcat.embed:tomcat-embed-jasper:${tomcatVersion}")
    }
}
