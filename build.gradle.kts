import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.20"
    application
}
group = "me.john"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit5"))
}

tasks {
    withType<KotlinCompile>() {
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
