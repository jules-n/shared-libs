stage('calculate versions') {
    env.CURRENT_VERSIONS = ['healthchecks', 'common-dtos'].collectEntries { subModuleName ->
        def version = sh("./gradlew -q :${subModuleName}:printVersion")
        [subModuleName: version - '-SNAPSHOT' - '-dirty']
    }
    env.NEXT_VERSIONS = env.CURRENT_VERSIONS.collectEntries { subModuleName, version ->
        // TODO: increment patch version (1.5.15 -> 1.5.16)

    }
}


stage('build healthchecks') {
    when {
        // "glob" pattern
        changes 'healthchecks/**'
    }
    steps {
        // 1. tag as ENV_NEXT_VERSIONS['healthchecks']
        // 2. run gradle build and test
        // 3. if everything is fine - push tag
        //     else delete tag
        // 4. build and push docker image
        // 5. run helm upgrade --install
    }
}
