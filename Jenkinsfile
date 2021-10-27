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
    }
}
