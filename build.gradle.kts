import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "me.john"
version = "1.0"

plugins {
    kotlin("jvm") version "1.5.20"
    application
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven") }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")
    testImplementation(kotlin("test-junit5"))
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
    test {
        useJUnitPlatform()
    }
}

kotlin {
    sourceSets {
        test {
            kotlin.srcDir("tests")
        }
        main {
            kotlin.srcDir("src")
        }

    }
}

application {
    mainClass.set("MainKt")
}
