import java.util.Date

val modId: String = (rootProject.findProperty("modBaseName")!! as String).toLowerCase()

plugins {
    kotlin("jvm") version "1.3.61"
    id("net.minecraftforge.gradle") version "3.0.161"
}

group = rootProject.findProperty("modGroup") as String
version = (rootProject.findProperty("modVersion") as String).toLowerCase()

base {
    archivesBaseName = modId
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val include: Configuration by configurations.creating

repositories {
    jcenter()
    maven("https://minecraft.curseforge.com/api/maven/") {
        name = "CurseForge"
    }
}

dependencies {

    // Minecraft Forge
    // https://files.minecraftforge.net/
    minecraft("net.minecraftforge:forge:1.15.2-31.1.0")

    // Kotlin
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("kottle:Kottle:1.4.0:slim")

    // JUnit
    testImplementation("junit:junit:4.12")
    
    // RxJava 3
    implementation("io.reactivex.rxjava3:rxjava:3.0.0")
}

minecraft {

    // Minecraft Coder Pack Channel + Version
    // http://export.mcpbot.bspk.rs/
    mappingChannel = "snapshot"
    mappingVersion = "20200215-1.15.1"
    accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))

    // For genIntelliJruns
    runs {
        listOf("client", "server").forEach {
            create(it) {

                workingDirectory = file("run").absolutePath

                property("forge.logging.markers", "SCAN")
                property("forge.logging.console.level", "info")

                mods {
                    create(modId) {
                        source(sourceSets.main.get())
                    }
                }
                ideaModule = "${project.name}.main"
            }
        }
    }
}

tasks.jar {
    manifest.attributes(
            "Specification-Title" to project.name,
            "Specification-Vendor" to "GuruTwit",
            "Specification-Version" to project.version,
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version,
            "Implementation-Vendor" to "GuruTwit",
            "Implementation-Timestamp" to Date()
    )
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}