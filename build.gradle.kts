// Build description for mywidgets, the GUI widget library.
//
// Everyday commands (run from the project folder):
//   ./gradlew build     compile and build build/libs/mywidgets.jar
//   ./gradlew deploy    build it and copy it to ../../lib/mywidgets.jar,
//                       where raceresults and myELH pick it up
//   ./gradlew run       start the mywidget_gui demo
//
// myContacts does not need deploy: it builds mywidgets from this source
// itself (see includeBuild in myContacts/settings.gradle.kts).

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

tasks.register<Copy>("deploy") {
    group = "distribution"
    description = "Builds mywidgets.jar and copies it to ../../lib for the IntelliJ-only projects"
    from(tasks.jar)
    into(layout.projectDirectory.dir("../../lib"))
}
