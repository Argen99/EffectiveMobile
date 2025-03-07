plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.devtools.ksp)
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    //Projects
    implementation(project(":core"))
    implementation(project(":features:main:domain"))
    implementation(project(":features:favorites:domain"))

    //Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.koin.android)
    implementation(libs.coroutines.android)
    implementation(libs.bundles.networking)
    implementation(libs.room.room)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
}