    import org.jetbrains.compose.desktop.application.dsl.TargetFormat
    import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
    import org.jetbrains.kotlin.gradle.dsl.JvmTarget

    plugins {
        alias(libs.plugins.kotlinMultiplatform)
        alias(libs.plugins.androidApplication)
        alias(libs.plugins.composeMultiplatform)
        alias(libs.plugins.composeCompiler)
        alias(libs.plugins.composeHotReload)
    }

    kotlin {
        androidTarget {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_11)
            }
        }

        listOf(
            iosArm64(),
            iosSimulatorArm64()
        ).forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = "ComposeApp"
                isStatic = true
            }
        }

        jvm()

        js {
            browser()
            binaries.executable()
        }

        @OptIn(ExperimentalWasmDsl::class)
        wasmJs {
            browser()
            binaries.executable()
        }

        sourceSets {
            androidMain.dependencies {
                implementation(libs.compose.uiToolingPreview)
                implementation(libs.androidx.activity.compose)
            }
            commonMain.dependencies {
                implementation(libs.bundles.compose.ui)
                implementation(libs.bundles.viewmodel)

                implementation(libs.compose.uiToolingPreview)

                implementation(project(":popups"))
            }
            commonTest.dependencies {
                implementation(libs.kotlin.test)
            }
            jvmMain.dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutinesSwing)
            }
        }
    }

    android {
        namespace = "com.tigranmirzoyan.popups"
        compileSdk = 36

        defaultConfig {
            applicationId = "com.tigranmirzoyan.popups"
            minSdk = 24
            targetSdk = 36
            versionCode = 1
            versionName = "1.0"
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }

    dependencies {
        debugImplementation(libs.compose.uiTooling)
    }

    compose.desktop {
        application {
            mainClass = "com.tigranmirzoyan.popups.MainKt"

            nativeDistributions {
                targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                packageName = "com.tigranmirzoyan.popups"
                packageVersion = "1.0.0"
            }
        }
    }