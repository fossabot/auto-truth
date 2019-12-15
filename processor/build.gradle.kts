import org.jetbrains.kotlin.konan.util.visibleName

/*
 * Copyright 2019 Tatsuya Maki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    val kotlinVersion = "1.3.61"
    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
}

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    // AutoTruth
    implementation(project(":annotations"))
    implementation("com.google.truth:truth:1.0")

    // AutoService
    val autoServiceVersion = "1.0-rc6"
    implementation("com.google.auto.service:auto-service-annotations:${autoServiceVersion}")
    kapt("com.google.auto.service:auto-service:${autoServiceVersion}")

    // Spek
    val spekVersion = "2.0.9"
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:${spekVersion}")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:${spekVersion}")

    // Compile Testing
    testImplementation("com.google.testing.compile:compile-testing:0.18")
}

tasks {
    val jvmTarget = "${JavaVersion.VERSION_1_8}"
    compileKotlin {
        kotlinOptions.jvmTarget = jvmTarget
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = jvmTarget
    }
}
