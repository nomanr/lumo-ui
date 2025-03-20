plugins {
    `kotlin-dsl`
    id("com.vanniktech.maven.publish") version "0.30.0"
}

group = "com.nomanr"
version = "1.2.2"

gradlePlugin {
    plugins {
        create("lumo") {
            id = "com.nomanr.plugin.lumo"
            implementationClass = "com.nomanr.lumo.plugin.LumoGradlePlugin"
        }
    }
}

extensions.configure<com.vanniktech.maven.publish.MavenPublishBaseExtension> {
    publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()

    coordinates(group.toString(), artifactId = "lumo", version = version.toString())

    pom {
        name.set("Lumo UI")
        description.set("A UI kit for Jetpack Compose, that you can copy-paste in your own projects")
        url.set("https://github.com/nomanr/lumo-ui")

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer {
                id.set("nomanr")
                name.set("Noman R")
                email.set("hello@nomanr.com")
            }
        }

        scm {
            connection.set("scm:git:https://github.com/nomanr/lumo-ui.git")
            developerConnection.set("scm:git:ssh://github.com/nomanr/lumo-ui.git")
            url.set("https://github.com/nomanr/lumo-ui")
        }
    }
}
