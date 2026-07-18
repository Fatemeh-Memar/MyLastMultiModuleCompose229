package com.example.mynewcompose

internal fun Project.configurePrintApksTask(extension: AndroidComponentsExtension<*, *, *>) {
    extension.onVariants { variant ->
        if (variant is HasAndroidTest) {
            val loader = variant.artifacts.getBuiltArtifactsLoader()
            val artifact = variant.androidTest?.artifacts?.get(SingleArtifact.APK)
            val javaSources = variant.androidTest?.sources?.java?.all
            val kotlinSources = variant.androidTest?.sources?.kotlin?.all

            val testSources = if (javaSources != null && kotlinSources != null) {
                javaSources.zip(kotlinSources) { javaDirs, kotlinDirs ->
                    javaDirs + kotlinDirs
                }
            } else {
                javaSources ?: kotlinSources
            }

            if (artifact != null && testSources != null) {
                tasks.register(
                    "${variant.name}PrintTestApk",
                    PrintApkLocationTask::class.java,
                ) {
                    apkFolder = artifact
                    builtArtifactsLoader = loader
                    variantName = variant.name
                    sources = testSources
                }
            }
        }
    }
}

@DisableCachingByDefault(because = "Prints output")
internal abstract class PrintApkLocationTask : DefaultTask() {

    @get:PathSensitive(PathSensitivity.RELATIVE)
    @get:InputDirectory
    abstract val apkFolder: DirectoryProperty

    @get:PathSensitive(PathSensitivity.RELATIVE)
    @get:InputFiles
    abstract val sources: ListProperty<Directory>

    @get:Internal
    abstract val builtArtifactsLoader: Property<BuiltArtifactsLoader>

    @get:Input
    abstract val variantName: Property<String>

    @TaskAction
    fun taskAction() {
        val hasFiles = sources.orNull?.any { directory ->
            directory.asFileTree.files.any {
                it.isFile && "build${File.separator}generated" !in it.parentFile.path
            }
        } ?: throw RuntimeException("Cannot check androidTest sources")

        // Don't print APK location if there are no androidTest source files
        if (!hasFiles) return

        val builtArtifacts = builtArtifactsLoader.get().load(apkFolder.get())
            ?: throw RuntimeException("Cannot load APKs")
        if (builtArtifacts.elements.size != 1) {
            throw RuntimeException("Expected one APK !")
        }
        val apk = File(builtArtifacts.elements.single().outputFile).toPath()
        println(apk)
    }
}
