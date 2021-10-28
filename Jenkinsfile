pipeline {
    agent any
    stages {
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
    }
}
