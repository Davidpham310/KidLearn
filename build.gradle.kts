// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
    }
}

plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22" apply false
    alias(libs.plugins.google.gms.google.services) apply false
}

// Configure Java toolchain for all projects
allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-Xjvm-target=17",
                "-opt-in=kotlin.RequiresOptIn"
            )
        }
    }
    
    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = JavaVersion.VERSION_17.toString()
        targetCompatibility = JavaVersion.VERSION_17.toString()
        options.isFork = true
        options.isIncremental = true
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
