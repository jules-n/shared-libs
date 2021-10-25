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
        // 1. tag as ENV_NEXT_VERSIONS['healthchecks'] - only if on "main" branch
        // GIT_BRANCH, (google for "jenkins GIT_BRANCH")
        // https://plugins.jenkins.io/git/

        // 2. run gradle build and test
        // 3. if everything is fine - push tag         - only if on "main" branch
        //     else delete tag
        // 4. ./gradlew publish
        //    publish should be configured for our nexus
        password = credentials('nexus-password-name')
        def rsaKey = credentials('jenkins-dev-event-receiver-id-rsa')
    }
}
