// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven {
            url = uri("https://maven.fabric.io/public")
        }

    }
    dependencies {
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.8.2.1")
        classpath("com.google.gms:google-services:4.4.2")
    }
}
