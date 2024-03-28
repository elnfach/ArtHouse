plugins {
    kotlin("kapt")
    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.elnfach.arthouse"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.elnfach.arthouse"
        minSdk = 26
        targetSdk = 34
        versionCode = 3
        versionName = "0.3.5"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:domain"))
    implementation(project(":core:storage"))
    implementation(project(":core:firebase"))

    // Dagger - Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.core)

    // Calendar
    implementation(libs.calendar)

    // State
    implementation(libs.state)

    // Date-Time
    implementation(libs.date.time)

    // State
    implementation(libs.list)

    // LiveData
    implementation(libs.lifecycle.livedata.ktx)

    // Coroutine
    implementation(libs.lifecycle.runtime.ktx)

    // Lifecycle
    implementation(libs.lifecycle.runtime.compose)

    // Navigation compose
    implementation(libs.navigation.compose)

    // Coil
    implementation(libs.coil.compose)

    implementation(libs.material)
    implementation(libs.core.ktx)

    implementation(platform(libs.kotlin.bom))
    implementation(platform(libs.compose.bom))
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}