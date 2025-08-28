import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.com.android.google.secrets.gradle)
    id("kotlin-kapt")
}

android {
    namespace = "com.llama.meetmind"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.llama.meetmind"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        val localProperties = gradleLocalProperties(rootDir)
//        manifestPlaceholders += mapOf(
//            "GOOGLE_GEMINI_KEY" to (localProperties["GOOGLE_GEMINI_KEY"] ?: ""),
//            "NAVER_NCP_KEY_ID" to (localProperties["NAVER_NCP_KEY_ID"] ?: ""),
//            "GOOGLE_PLACE_SDK_KEY" to (localProperties["GOOGLE_PLACE_SDK_KEY"] ?: "")
//        )

        val googlePlaceSdkKey = localProperties["GOOGLE_PLACE_SDK_KEY"] as? String ?: ""
        buildConfigField("String", "GOOGLE_PLACE_SDK_KEY", "\"${googlePlaceSdkKey}\"")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":presentation"))

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    implementation(libs.datastore)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.serialization)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp)

    implementation(libs.naver.maps.sdk)

    // google place
    implementation(libs.google.place.sdk)
    implementation(libs.google.place.ktx)

    // for instrumentation tests
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.48.1")
//    kaptAndroidTest("com.google.dagger:hilt-compiler:2.48.1")

    // for Local unit tests
    testImplementation("com.google.dagger:hilt-android-testing:2.48.1")
//    kaptTest("com.google.dagger:hilt-compiler:2.48.1")

    testImplementation("org.robolectric:robolectric:4.11.1")

    // hilt+ work
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("androidx.hilt:hilt-work:1.2.0")
    kapt("androidx.hilt:hilt-compiler:1.2.0")

    // hilt
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
}