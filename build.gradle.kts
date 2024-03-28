buildscript {
    dependencies {
        classpath(libs.gradle)
        classpath(libs.google.services)
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.google.gms.google-services") version "4.4.1" apply false
    id("com.google.dagger.hilt.android") version "2.49" apply false
    id("org.jetbrains.kotlin.jvm") version "1.7.20" apply false
    id("com.android.library") version "8.3.1" apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}