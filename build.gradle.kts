// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}
buildscript {
    repositories {
        mavenCentral()
        }
    dependencies {
        classpath("io.objectbox:objectbox-gradle-plugin:3.8.0")
        }
    }
