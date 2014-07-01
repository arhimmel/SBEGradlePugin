package com.arhimmel.gradle.plugins.SBE

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class SBEGenerate extends DefaultTask {

    String configName

    @Input
    def includeDirs = []

    @TaskAction
    def generate() {

    }

    /*
    task createSBEClasses(type: JavaExec) {
        description = "generate SBE files"

        classpath sourceSets.generated.runtimeClasspath
        main = "uk.co.real_logic.sbe.SbeTool"
        File res = file("src/main/resources")

        FileTree tree = fileTree(dir: "src/main/resources").matching {
            include "schemas/**"
        }


        args = tree.getFiles()

        jvmArgs = ["-Dsbe.output.dir=src/generated/java"]

    }
    */
}
