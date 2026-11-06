// Build description for mywidgets, the GUI widget library.
//
// Everyday commands (run from the project folder):
//   ./gradlew build     compile and build build/libs/mywidgets.jar
//   ./gradlew run       start the mywidget_gui demo
//
// myContacts and myELH build mywidgets from this source themselves
// (see includeBuild in their settings.gradle.kts), so no jar is copied anywhere.

plugins {
    // "java-library" is the java plugin for code that other projects use
    `java-library`
    application
}

// Other builds refer to this library as "org.lerot:mywidgets"
group = "org.lerot"
version = "1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

// The source lives directly in src/ rather than src/main/java
sourceSets {
    main {
        java.setSrcDirs(listOf("src"))
        resources.setSrcDirs(emptyList<String>())
    }
}

dependencies {
    // the look and feel; projects using mywidgets get it automatically
    implementation("com.formdev:flatlaf:3.7.2")
    // works out the vertical and horizontal panel layouts (see jswMigLayout)
    implementation("com.miglayout:miglayout-swing:11.5.1")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-Xlint:deprecation")
}

application {
    mainModule = "mywidgets"
    mainClass = "org.lerot.mywidgets.mywidget_gui"
}

tasks.jar {
    archiveFileName = "mywidgets.jar"
    manifest {
        attributes["Main-Class"] = application.mainClass
    }
}
