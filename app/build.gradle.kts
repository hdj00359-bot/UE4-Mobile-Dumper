plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.bizzra.dumper"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.bizzra.dumper"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        multiDexEnabled = false

        ndk {
            abiFilters += listOf("arm64-v8a", "armeabi-v7a")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    externalNativeBuild {
        ndkBuild {
            path = file("src/main/jni/Android.mk")
        }
    }
    ndkVersion = "25.1.8937393"
}

dependencies {}

tasks.register<Zip>("packageReleaseSoDex") {
    dependsOn("assembleRelease")
    archiveFileName.set("ue4-dumper-${android.defaultConfig.versionName ?: "unknown"}-so-dex.zip")
    destinationDirectory.set(layout.buildDirectory.dir("outputs/so-dex"))

    from(zipTree(layout.buildDirectory.file("outputs/apk/release/app-release.apk"))) {
        include("classes*.dex")
        include("lib/**")
    }
}