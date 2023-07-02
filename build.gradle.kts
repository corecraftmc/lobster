plugins {
    id("paper-plugin")
}

dependencies {
    implementation("com.ryderbelserion.stick", "stick-paper", "2.2.1-snapshot") {
        exclude("com.google.code.gson", "gson")
        exclude("me.carleslc.Simple-YAML", "Simple-Yaml")
        exclude("org.jetbrains", "annotations")
    }
}

tasks {
    reobfJar {
        val file = File("$rootDir/jars")

        if (!file.exists()) file.mkdirs()

        outputJar.set(layout.buildDirectory.file("$file/${rootProject.name}-${rootProject.version}.jar"))
    }

    shadowJar {
        fun reloc(pkg: String) = relocate(pkg, "${rootProject.group}.dep.$pkg")

        reloc("com.ryderbelserion.stick")
    }

    processResources {
        filesMatching("paper-plugin.yml") {
            expand(
                "name" to rootProject.name,
                "group" to rootProject.group,
                "version" to rootProject.version,
                "description" to rootProject.description
            )
        }
    }
}