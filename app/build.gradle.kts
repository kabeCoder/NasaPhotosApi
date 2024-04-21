plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.composeDestinations)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.kabe.nasaphotosapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kabe.nasaphotosapi"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    flavorDimensions += "environment"
    productFlavors{

        create("mock"){
            dimension = "environment"
            applicationIdSuffix = ".mock"
            versionNameSuffix = "-mock"
            buildConfigField("String", "BASE_URL", "\"https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/\"")
            buildConfigField("String", "API_KEY", "\"htzDUej1O2myh7zWWTAAv05WD5Te5mJZyKNHIMVX\"")
        }
        create("prod"){
            dimension = "environment"
            applicationIdSuffix = ".prod"
            versionNameSuffix = "-prod"
            buildConfigField("String", "BASE_URL", "\"https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/\"")
            buildConfigField("String", "API_KEY", "\"htzDUej1O2myh7zWWTAAv05WD5Te5mJZyKNHIMVX\"")
        }
    }

    androidComponents {
        beforeVariants { variantBuilder ->
            if (variantBuilder.name == "mockRelease" || variantBuilder.name == "prodDebug") {
                variantBuilder.enable = false
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Constraint Layout
    implementation(libs.androidx.constraintlayout.compose)

    // Compose Destinations
    implementation(libs.compose.destinations.core)
    ksp(libs.compose.destinations.ksp)

    // Dagger Hilt
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.android.compiler)

    //Hilt Navigation Compose
    implementation(libs.androidx.hilt.navigation.compose)

    // Retrofit
    implementation (libs.squareup.retrofit2.retrofit)
    implementation (libs.squareup.retrofit2.converter.gson)
    implementation (libs.squareup.okhttp3)
    implementation (libs.squareup.gson)

    // Room
    implementation (libs.androidx.room)
    implementation (libs.androidx.room.ktx)
    ksp (libs.androidx.room.compiler)
    annotationProcessor (libs.androidx.room.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines)

    // Coil
    implementation(libs.coil)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}