plugins {
    alias(libs.plugins.newcompose.android.library)
    alias(libs.plugins.newcompose.android.library.compose)
    alias(libs.plugins.newcompose.android.library.jacoco)
    alias(libs.plugins.roborazzi)



}

android {
    namespace = "com.example.newcompose.core.designsystem"
    testOptions.unitTests.isIncludeAndroidResources = true
}

dependencies {
    lintPublish(projects.lint)
   /* lintPublish(projects.lint)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)*/
}