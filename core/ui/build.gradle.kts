plugins {
    alias(libs.plugins.newcompose.android.library)
    alias(libs.plugins.newcompose.android.library.compose)
    alias(libs.plugins.newcompose.android.library.jacoco)
}
android {
    namespace = "com.example.core.ui"
}

dependencies {
    api(libs.androidx.metrics)
    //api(projects.core.analytics)  //TODO HANDLE THIS
    api(projects.core.designsystem)
    api(projects.core.model)

    implementation(libs.androidx.browser)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)

    androidTestImplementation(libs.bundles.androidx.compose.ui.test)
    //androidTestImplementation(projects.core.testing)  //TODO HANDLE THIS
}
