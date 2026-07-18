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

        register("androidLint") {
            id = libs.plugins.newcompose.android.lint.get().pluginId
            implementationClass = "AndroidLintConventionPlugin"
        }

        register("androidApplication"){
            id=libs.plugins.newCompose.android.application.get().pluginId
            implementationClass="AndroidApplicationConventionPlugin"
        }

    }

}
