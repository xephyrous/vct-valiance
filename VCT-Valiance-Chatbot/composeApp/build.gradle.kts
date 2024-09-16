import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }

    
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            val core = "0.5.3"
            implementation("org.kotlincrypto.core:digest:$core")
            implementation("org.kotlincrypto.core:mac:$core")
            implementation("org.kotlincrypto.core:xof:$core")

            implementation(project.dependencies.platform("org.kotlincrypto.macs:bom:0.5.3"))
            implementation("org.kotlincrypto.macs:hmac-sha2")

            implementation(project.dependencies.platform("org.kotlincrypto.hash:bom:0.5.3"))
            implementation("org.kotlincrypto.hash:sha2")
        }
    }
}

compose.resources {
    publicResClass = false
    packageOfResClass = "vctvaliancechatbot.composeapp.generated.resources"
    generateResClass = auto
}