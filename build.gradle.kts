import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val vaadinonkotlin_version = "0.15.0"
val vaadin_version = "24.1.0.beta2"
val slf4j_version = "2.0.6"

plugins {
    kotlin("jvm") version "1.8.21"
    id("application")
    id("com.vaadin") version "24.1.0.beta2"
}

defaultTasks("clean", "build")

repositories {
    maven { setUrl("https://maven.vaadin.com/vaadin-prereleases") }
    mavenCentral()
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        // when tests fail, we want to see the exceptions on stdout
        exceptionFormat = TestExceptionFormat.FULL
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

dependencies {
    // Vaadin
    implementation("eu.vaadinonkotlin:vok-framework-vokdb:$vaadinonkotlin_version") {
        exclude(group = "com.vaadin")
    }
    implementation("com.vaadin:vaadin-core:${vaadin_version}") {
        afterEvaluate {
            if (vaadin.productionMode) {
                exclude(module = "vaadin-dev")
            }
        }
    }
    implementation("com.github.mvysny.vaadin-boot:vaadin-boot:11.3")

    // validator. We need to explicitly declare it since we're using annotations from it
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")

    // logging
    // currently we are logging through the SLF4J API to slf4j-simple. See src/main/resources/simplelogger.properties file for the logger configuration
    implementation("org.slf4j:slf4j-simple:$slf4j_version")

    implementation(kotlin("stdlib-jdk8"))

    // db
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("org.flywaydb:flyway-core:9.15.2")
    implementation("com.h2database:h2:2.1.214")

    // test support
    testImplementation("com.github.mvysny.kaributesting:karibu-testing-v23:2.0.2")
    testImplementation("com.github.mvysny.dynatest:dynatest:0.24")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

application {
    mainClass.set("com.vaadin.pwademo.MainKt")
}
