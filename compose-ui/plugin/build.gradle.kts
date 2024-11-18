plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create("compose") {
            id = "com.nomanr.plugin.composeui"
            implementationClass = "com.nomanr.composeui.plugin.ComposeUIGradlePlugin"
        }
    }
}



