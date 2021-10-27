pipeline {
    agent any
    stages {
        stage('calculate versions') {
            steps {
                script {
                    env.CURRENT_VERSIONS = [
                            'non-functional-lib',
                            'common-dtos',
                            'converters',
                            'proto-files',
                            'sending-lib',
                            'cache-lib',
                            'healthchecks'
                    ].collectEntries { subModuleName ->
                        echo "getting current version: ${subModuleName}"
                        def version = sh("./gradlew -q :${subModuleName}:printVersion")
                        echo "current version : ${subModuleName} - ${version}"
                        [subModuleName: version - '-SNAPSHOT' - '-dirty']
                    }
                    env.NEXT_VERSIONS = env.CURRENT_VERSIONS.collectEntries { subModuleName, version ->
                        def nextVersion = incrementVersion(version)
                        echo "next version : ${subModuleName} - ${nextVersion}"
                        [(subModuleName), nextVersion]
                    }
                }
            }
        }
        stage('build healthchecks') {
            when {
                allOf {
                    branch 'origin/main';
                    // "glob" pattern
                    //changes 'healthchecks/**'
                }
            }
            steps {
                script {
                    // 1. tag as ENV_NEXT_VERSIONS['healthchecks'] - only if on "main" branch
                    sh(script: "git tag $tagName")
                    // GIT_BRANCH, (google for "jenkins GIT_BRANCH")
                    // https://plugins.jenkins.io/git/
                    try {
                        sh './gradlew build -x test'
                        sh './gradlew test'
                        echo 'everything ok'
                    } catch (e) {
                        sh(script: "git tag -d $tagName")
                        return
                    }
                    sh(script: "git push origin --tags")
                    withCredentials([usernamePassword(credentialsId: '', usernameVariable: '', passwordVariable: '')]) {
                        sh "./gradlew healthchecks:artifactoryPublish"
                    }
                    // 3. if everything is fine - push tag         - only if on "main" branch
                    //     else delete tag
                    // 4.
                    //    publish should be configured for our nexus
                    /* password = credentials('nexus-password-name')
                    def rsaKey = credentials('jenkins-dev-event-receiver-id-rsa') */
                }
            }
        }
    }
}

def incrementVersion(String version, boolean incMajor = false, boolean incMinor = false, boolean incPatch = true) {
    def parts = version.split('\\.')
    def major = parts[0] as int
    def minor = parts[1] as int
    def patch = parts[2] as int
    incMajor ? "${major + 1}.0.0"
            : incMinor ? "${major}.${minor + 1}.0"
            : "${major}.${minor}.${patch + 1}"
}
