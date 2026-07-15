import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`

}

gradlePlugin {

    plugins{

        register("androidLibrary"){
            id = libs.plugins.newcompose.android.library.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }

    }

}
