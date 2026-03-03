# 🍞 KmpToast

A lightweight, physics-based, and fully customizable Pop-up/Toast library for **Compose Multiplatform**.

## ✨ Features

* **Built-in Types**: Ready-to-use styles for `Success`, `Error`, `Warning`, `Info`, and `Default` states.
* **Easy Theming**: Override colors and icons globally using the `PopUpStyle` interface without rebuilding the UI.
* **Slot API**: Pass any Composable to `PopUpHost` for total design freedom.
* **Physics-based Gestures**: Smooth swipe-to-dismiss with density-aware thresholds.
* **Smart Stacking**: Automatically manages a queue of notifications with refined vertical animations.
* **Lifecycle Aware**: Built on top of `StateFlow` and `collectAsStateWithLifecycle`.

## 🚀 Installation

### 1. Add the JitPack repository

In your root `settings.gradle.kts`, add the JitPack repository to the `dependencyResolutionManagement` block:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### 2. Add the dependency

In your shared module (usually `composeApp/build.gradle.kts` or `shared/build.gradle.kts`), add the library to your `commonMain` dependencies:

```kotlin
sourceSets {
    commonMain.dependencies {
        // Replace '1.0.1' with the latest release version
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

Trigger a popup from anywhere in your codebase using the `PopUpManager`. You can specify the text, duration, and type.

```kotlin
// Show a standard success message
PopUpManager.showMessage(
    text = "Settings saved successfully!", 
    duration = PopUpDuration.Short,
    type = PopUpType.Success
)

// Show a persistent error message
PopUpManager.showMessage(
    text = "Connection failed. Please try again.", 
    duration = PopUpDuration.Long,
    type = PopUpType.Error
)
```

## 🎨 Customization

KmpToast offers two levels of customization depending on your needs.

### Level 1: Quick Theming (PopUpStyle)
If you like the default layout but want to change the colors or icons to match your brand, simply implement the `PopUpStyle` interface and pass it to your host.

```kotlin
val myCustomStyle = object : PopUpStyle {
    override fun backgroundColor(type: PopUpType): Color = when(type) {
        PopUpType.Success -> Color(0xFF00BFA5) // Custom Brand Teal
        else -> DefaultPopUpStyle.backgroundColor(type) // Fallback to defaults
    }

    override fun icon(type: PopUpType): ImageVector? = DefaultPopUpStyle.icon(type)
}

PopUpHost(style = myCustomStyle) {
    MainAppContent()
}
```

### Level 2: Full UI Override (Slot API)
If you need a completely custom layout (e.g., adding a progress bar, custom buttons, or different shapes), pass your own Composable to the `popupContent` parameter.

*Note: The `LocalPopUpStyle` CompositionLocal is available if you want to read the current style colors inside your custom UI!*

```kotlin
PopUpHost(
    popupContent = { message -> 
        MyCustomAppToast(
            text = message.text,
            isError = message.type == PopUpType.Error
        ) 
    }
) {
    MainAppContent()
}
```