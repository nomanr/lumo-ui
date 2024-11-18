package com.nomanr.composeui.plugins

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

open class PublishingPluginExtension {
    var publishGroupId: String = "com.nomanr"
    var publishVersion: String = "0.0.1-alpha1"
    var artifactId: String = "composeui"
    var githubUrl: String = "github.com/nomanr/compose-ui"
    var developerId: String = "nomanr"
    var developerName: String = "Noman R"
    var developerEmail: String = "hello@nomanr.com"
}

class PublishingPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("com.vanniktech.maven.publish")

        val extension = project.extensions.create<PublishingPluginExtension>("publishingPlugin")

        project.afterEvaluate {
            configureMavenPublishing(project, extension)
        }
    }

    private fun configureMavenPublishing(project: Project, extension: PublishingPluginExtension) {
        project.extensions.configure(MavenPublishBaseExtension::class.java) {
            publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
            signAllPublications()

            coordinates(extension.publishGroupId, extension.artifactId.ifEmpty { project.name }, extension.publishVersion)

            pom {
                name.set("Compose Ui")
                description.set("A UI kit for Jetpack Compose, that you can copy-paste in your own projects")
                url.set("https://${extension.githubUrl}")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        id.set(extension.developerId)
                        name.set(extension.developerName)
                        email.set(extension.developerEmail)
                    }
                }

                scm {
                    connection.set("scm:git:https://${extension.githubUrl}.git")
                    developerConnection.set("scm:git:ssh://${extension.githubUrl}.git")
                    url.set("https://${extension.githubUrl}")
                }
            }
        }
    }
}
