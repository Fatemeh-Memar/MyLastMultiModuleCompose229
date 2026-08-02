plugins {
    alias(libs.plugins.newcompose.jvm.library)
    alias(libs.plugins.newcompose.hilt)
}
 dependencies{
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}