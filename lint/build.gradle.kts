import org.jetbrains.kotlin.gradle.dsl.JvmTarget


plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.newcompose.android.lint)

}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}
dependencies {
    compileOnly(libs.kotlin.stdlib)
    compileOnly(libs.lint.api)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.lint.checks)
    testImplementation(libs.lint.tests)
}
