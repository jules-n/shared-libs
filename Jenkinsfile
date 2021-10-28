pipeline {
    agent any
    stages {
        stage('cache-lib') {
            when {
                allOf {
                    branch comparator: 'REGEXP', pattern: 'main'
                    // "glob" pattern
                    changeset 'cache-lib/**'
                }
            }
            steps {
                build job: "shared-libs-cache-lib/${BRANCH_NAME}", wait: false
            }
        }
        stage('common-dtos') {
            when {
                allOf {
                    branch comparator: 'REGEXP', pattern: 'main'
                    // "glob" pattern
                    changeset 'common-dtos/**'
                }
            }
            steps {
                build job: "shared-libs-common-dtos/${BRANCH_NAME}", wait: false
            }
        }
        stage('converters') {
            when {
                allOf {
                    branch comparator: 'REGEXP', pattern: 'main'
                    // "glob" pattern
                    changeset 'converters/**'
                }
            }
            steps {
                build job: "shared-libs-converters/${BRANCH_NAME}", wait: false
            }
        }
        stage('healthchecks') {
            when {
                allOf {
                    branch comparator: 'REGEXP', pattern: 'main'
                    // "glob" pattern
                    changeset 'healthchecks/**'
                }
            }
            steps {
                build job: "shared-libs-healthchecks/${BRANCH_NAME}", wait: false
            }
        }
        stage('non-functional-lib') {
            when {
                allOf {
                    branch comparator: 'REGEXP', pattern: 'main'
                    // "glob" pattern
                    changeset 'non-functional-lib/**'
                }
            }
            steps {
                build job: "shared-libs-non-functional-lib/${BRANCH_NAME}", wait: false
            }
        }
        stage('proto-files') {
            when {
                allOf {
                    branch comparator: 'REGEXP', pattern: 'main'
                    // "glob" pattern
                    changeset 'proto-files/**'
                }
            }
            steps {
                build job: "shared-libs-proto-files/${BRANCH_NAME}", wait: false
            }
        }
        stage('sending-lib') {
            when {
                allOf {
                    branch comparator: 'REGEXP', pattern: 'main'
                    // "glob" pattern
                    changeset 'sending-lib/**'
                }
            }
            steps {
                build job: "shared-libs-sending-lib/${BRANCH_NAME}", wait: false
            }
        }
    }
}
