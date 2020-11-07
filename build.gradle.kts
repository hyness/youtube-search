import org.gradle.api.JavaVersion.*

plugins {
    id("jacoco")
    id("org.sonarqube") version "2.8"
    id("org.springframework.boot") version "2.3.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("com.github.kt3k.coveralls") version "2.9.0"
    id("org.siouan.frontend-jdk11") version "4.0.1"
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.4.10"
}

group = "org.freshlegacycode"
java.sourceCompatibility = VERSION_11
val frontEndDir =  "${projectDir}/src/main/frontend"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.hibernate.validator:hibernate-validator")
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

tasks{
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = java.sourceCompatibility.majorVersion
        }
    }

    processResources {
        dependsOn("copyFrontendToBuild")
    }

    bootJar {
        layered()
    }

    test {
        useJUnitPlatform()
        finalizedBy("jacocoTestReport")
    }

    jacocoTestReport {
        reports {
            xml.isEnabled = true
            html.isEnabled = false
        }
    }

    create<Copy>("copyFrontendToBuild") {
        from("$frontEndDir/build")
        into("${buildDir}/resources/main/static")
    }
}

frontend {
    nodeVersion.set("15.1.0")
    nodeInstallDirectory.set(file("$projectDir/.gradle/node"))
    packageJsonDirectory.set(file(frontEndDir))
    assembleScript.set("run build")
    cleanScript.set("run clean")
}

coveralls {
    sourceDirs = listOf("src/main/kotlin")
}
