package com.arhimmel.gradle.plugins.SBE

import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.TaskAction


class SBEGenerate extends JavaExec {

    String outputDir

    @TaskAction
    def void exec() {


        jvmArgs = ["-Dsbe.output.dir=$outputDir"]

        logger.warn(super.commandLine.toString())
        super.exec()
    }
}
