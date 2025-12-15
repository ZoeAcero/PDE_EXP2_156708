// build.gradle.kts (Raíz del proyecto: PDE_EXP2_156708)

// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    // Plugins ya existentes (se usan IDs y versiones explícitas para evitar referencias a `libs` no resueltas)
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false

    // 1. Plugin de Google Services: Necesario para Firebase
    id("com.google.gms.google-services") version "4.3.15" apply false

    // 2. Plugin KAPT: Necesario para procesadores de anotaciones (como Glide)
    id("org.jetbrains.kotlin.kapt") version "1.9.10" apply false
}
