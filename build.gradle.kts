import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath("org.siouan:frontend-gradle-plugin-jdk11:4.0.1")
    }
}


apply(plugin = "org.siouan.frontend-jdk11")

plugins {
    id("jacoco")
    id("org.sonarqube") version "2.8"
    id("org.springframework.boot") version "2.2.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("com.github.kt3k.coveralls") version "2.9.0"
    id("org.siouan.frontend-jdk11") version "4.0.1"
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
}
group = "org.freshlegacycode"
version = "1.0-SNAPSHSHOT"

java.sourceCompatibility = JavaVersion.VERSION_11

tasks.bootJar {
    archiveVersion.set("")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.webjars:webjars-locator:0.33")
    implementation("org.webjars:jquery:1.12.1")
    implementation("org.webjars:bootstrap:3.3.7")
    compileOnly("org.springframework.boot:spring-boot-configuration-processor")
//    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

val frontEndDir =  "${projectDir}/src/main/frontend"
frontend {
    nodeVersion.set("10.16.2")
    nodeInstallDirectory.set(file("$projectDir/.gradle/node"))
    packageJsonDirectory.set(file(frontEndDir))
    assembleScript.set("run build")
    cleanScript.set("ci")
    checkScript.set("run check")
}

tasks.test {
    finalizedBy("jacocoTestReport")
}

tasks.withType<ProcessResources> {
    dependsOn("copyFrontendToBuild")
}

tasks.create<Copy>("copyFrontendToBuild") {
    from("$frontEndDir/build")
    into("${buildDir}/resources/main/static")
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        html.isEnabled = false
    }
}

coveralls {
    sourceDirs = listOf("src/main/kotlin")
}
