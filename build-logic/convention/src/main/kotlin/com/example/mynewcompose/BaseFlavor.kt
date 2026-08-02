package com.example.mynewcompose

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor
import org.gradle.kotlin.dsl.invoke

@Suppress("EnumEntryName")
enum class FlavorDimension {
    environment,
}

@Suppress("EnumEntryName")
enum class BaseFlavor(val dimension: FlavorDimension, val applicationIdSuffix: String? = null) {
    dev(FlavorDimension.environment, applicationIdSuffix = ".dev"),
    prod(FlavorDimension.environment),
}

fun configureFlavors(
    commonExtension: CommonExtension,
    flavorConfigurationBlock: ProductFlavor.(flavor: BaseFlavor) -> Unit = {},
) {
    commonExtension.apply {
        FlavorDimension.entries.forEach { flavorDimension ->
            flavorDimensions += flavorDimension.name
        }

        productFlavors {
            BaseFlavor.entries.forEach { baseFlavor ->
                register(baseFlavor.name) {
                    dimension = baseFlavor.dimension.name
                    flavorConfigurationBlock(this, baseFlavor)
                    if (commonExtension is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (baseFlavor.applicationIdSuffix != null) {
                            applicationIdSuffix = baseFlavor.applicationIdSuffix
                        }
                    }
                }
            }
        }
    }
}
