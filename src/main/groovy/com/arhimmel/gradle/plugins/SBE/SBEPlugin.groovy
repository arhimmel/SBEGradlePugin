package com.arhimmel.gradle.plugins.SBE

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.tasks.SourceSet
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SBEPlugin implements Plugin<Project> {

//    String SBE_CONFIGURATION_NAME = "sbe"

    Logger logger = LoggerFactory.getLogger(SBEPlugin.class)

    //compile 'uk.co.real-logic:sbe:1.0.1-RC2'

    @Override
    void apply(final Project project) {

        project.apply plugin: 'java'

//        Configuration sbeConfiguration = project.getConfigurations()
//                .create(SBE_CONFIGURATION_NAME)
//                .setVisible(false).setTransitive(false)
//                .setDescription("Task to run sbe")




        addJarDependencies(project)

//        SBEConvention convention = new SBEConvention(project)
//        project.convention.plugins.sbe = convention


        project.sourceSets.all { SourceSet sourceSet ->

            if (!sourceSet.name.equals("sbe")) {
                logger.warn("skipping: $sourceSet.name")
                return;
            }

            logger.warn("using: $sourceSet.name")

            addGenerateTask(project, sourceSet)
        }

//        project.sourceSets.all { SourceSet sourceSet ->
//
//            if (sourceSet.name.equals("main")) {
//                sourceSet.getJava().srcDir(
//                        project.configurations.getByName("sbeRuntime")
//                )
//            }
//        }

    }

    private void addGenerateTask(Project project, SourceSet sourceSet) {
        def generateSBETaskName = sourceSet.getTaskName('generate', 'sbe')

        //add task to generate .java files
        project.tasks.create(generateSBETaskName, SBEGenerate) {
            description = "Compiles SBE xml files for source set: $sourceSet.name"

            outputDir = "$project.buildDir/generated-sources"

            classpath sourceSet.runtimeClasspath
            main = "uk.co.real_logic.sbe.SbeTool"
            args = sourceSet.resources.files
        }
    }

    private static void addJarDependencies(Project project) {
        //ToDo get this from a config
        def version = "1.0.1-RC2"
        Dependency dependency = project.dependencies.create("uk.co.real-logic:sbe:$version")
        Configuration sbeConfig = project.configurations.create('sbeRuntime')

        sbeConfig.incoming.beforeResolve {
            sbeConfig.dependencies.add(dependency)
        }
    }

}
