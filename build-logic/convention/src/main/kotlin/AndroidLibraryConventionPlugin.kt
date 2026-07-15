
import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        apply(plugin = "com.android.library")
        //apply(plugin = "nowinandroid.android.lint")

        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(this)
            testOptions.targetSdk = 36
            lint.targetSdk = 36
            defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            testOptions.animationsDisabled = true
            configureFlavors(this)
            configureGradleManagedDevices(this)
            // The resource prefix is derived from the module name,
            // so resources inside ":core:module1" must be prefixed with "core_module1_"
            resourcePrefix =
                path.split("""\W""".toRegex()).drop(1).distinct().joinToString(separator = "_")
                    .lowercase() + "_"
        }
    }
}