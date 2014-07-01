package com.arhimmel.gradle.plugins.SBE

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.tasks.SourceSet

class SBEPlugin implements Plugin<Project> {

    @Override
    void apply(final Project project) {

//        Configuration sbeConfig = project.configurations.getByName('sbe')
//        sbeConfig.getAllDependencies()

        project.apply plugin: 'java'
        project.convention.plugins.sbe = new SBEConvention(project)

        project.sourceSets.all { SourceSet sourceSet ->

            def generateSBETaskName = sourceSet.getTaskName('generate', 'sbe')

            project.tasks.create(generateSBETaskName, SBEGenerate) {
                description = "Compiles SBE xml files source '${sourceSet.name}"
                inputs.source project.fileTree("src/{$sourceSet.name}/sbe") { include "**/*.xml" }
                outputs.dir "{$project.buildDir}/{$sourceSet.name}/generated"
            }
        }

    }

}
