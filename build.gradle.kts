import com.moowork.gradle.node.npm.NpmInstallTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.moowork.gradle.node.npm.NpmTask

plugins {
    id("jacoco")
    id("org.sonarqube") version "2.8"
    id("org.springframework.boot") version "2.2.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("com.github.kt3k.coveralls") version "2.9.0"
//    id("com.moowork.node") version "1.3.1"
    id("com.github.node-gradle.node") version "2.2.0"
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
}

group = "org.freshlegacycode"
version = "1.0-SNAPSHSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

val developmentOnly by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
}

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
    developmentOnly("org.springframework.boot:spring-boot-devtools")
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
node {
    version = "10.16.2"
    npmVersion = "6.13.4"
    download = true
}

tasks.test {
    finalizedBy("jacocoTestReport")
}

tasks.create<NpmTask>("npmBuild") {
    setWorkingDir(file("${projectDir}/src/main/frontend"))
    setArgs(listOf("run", "build"))
}

tasks.withType<ProcessResources> {
    dependsOn("copyFrontendToBuild")
}

tasks.create<Copy>("copyFrontendToBuild") {
    dependsOn("npmBuild")
    from("${projectDir}/src/main/frontend/build")
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
