# 🍞 KmpToast

A lightweight, physics-based, and fully customizable Pop-up/Toast library for **Compose
Multiplatform**.

## ✨ Features

* **Slot API**: Pass any Composable to `PopUpHost` for total design freedom.
* **Physics-based Gestures**: Smooth swipe-to-dismiss with density-aware thresholds.
* **Smart Stacking**: Automatically manages a queue of notifications with refined vertical
  animations.
* **Lifecycle Aware**: Built on top of `StateFlow` and `collectAsStateWithLifecycle`.


### 🚀 Installation

1. Add the JitPack repository

   In your root settings.gradle.kts, add the JitPack repository to the
   dependencyResolutionManagement block:

    ```kotlin
    dependencyResolutionManagement {
        repositories {
            google()
            mavenCentral()
            maven { url = uri("https://jitpack.io") } // Add this line
        }
    }
    ```

2. Add the dependency

   In your shared module (usually composeApp/build.gradle.kts or shared/build.gradle.kts), add the
   library to your commonMain dependencies:

    ```kotlin
    sourceSets {
        commonMain.dependencies {
            // Replace 'TAG' with the latest version (e.g., 1.0.0)
            implementation("com.github.TigranMirzoyan.KmpToast:popups:1.0.1")
        }
    }
    ```

## 🛠️ Usage

### 1. Wrap your UI

Place the `PopUpHost` at the root of your application.

```kotlin
PopUpHost {
    MainAppContent()
}
```

### 2. Show a Message

Trigger a popup from anywhere in your codebase using the PopUpManager.

```kotlin
PopUpManager.showMessage("Settings saved!", PopUpDuration.SHORT)
```

### 🎨 Custom Pop-ups

Want a custom look? Just pass your own Composable:

```kotlin
PopUpHost(
    popupContent = { message -> MyCustomUI(message) }
) {
    MainAppContent()
}
```