SBEGradlePugin
==============

Basic build.gradle config:
```groovy
    buildscript {
        repositories {
            mavenLocal()
            mavenCentral()
        }
        dependencies {
            classpath 'com.arhimmel.gradle.plugins:SBEPlugin:0.0.1'
        }
    }

    apply plugin: 'sbe'

    configurations {
        sbe
    }

    sourceSets {

        sbe {
            resources {
           }
        }
    }

```
