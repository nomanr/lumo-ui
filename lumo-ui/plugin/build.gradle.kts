plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create("lumo") {
            id = "com.nomanr.plugin.lumo"
            implementationClass = "com.nomanr.lumo.plugin.LumoGradlePlugin"
        }
    }
}



