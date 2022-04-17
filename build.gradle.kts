plugins {
    `java-library`
    id("io.papermc.paperweight.userdev") version "1.3.4"
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "ga.uniquecoding"
version = "0.0.1"

java {
    // Configure the java toolchain. This allows gradle to auto-provision JDK 17 on systems that only have JDK 8 installed for example.
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://jitpack.io")
    maven("https://repo.codemc.org/repository/maven-public/")
    maven("https://repo.kryptonmc.org/releases")

}

dependencies {
    implementation("dev.jorel.CommandAPI:commandapi-shade:6.5.4")           // Command API
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")          // Paper API
    compileOnly("me.neznamy", "tab-api", "3.0.2")                        // TAB API

    paperDevBundle("1.18.2-R0.1-SNAPSHOT")                                  // PaperWeight
}

tasks {
    // Configure reobfJar to run when invoking the build task
    assemble {
        dependsOn(reobfJar)
    }


    compileJava {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything

        // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
        // See https://openjdk.java.net/jeps/247 for more information.
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything

        // Placeholders for plugin.yml
        filesMatching("**/plugin.yml") {
            expand(rootProject.project.properties)
        }

        // Always re-run this task
        outputs.upToDateWhen { false }
    }


    reobfJar {
        // This is an example of how you might change the output location for reobfJar. It's recommended not to do this
        // for a variety of reasons, however it's asked frequently enough that an example of how to do it is included here.
        outputJar.set(layout.buildDirectory.file("libs/PaperweightTestPlugin-${project.version}.jar"))
    }
}