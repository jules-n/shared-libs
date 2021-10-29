pipeline {
    agent any
    parameters {
        booleanParam(name: 'buildCacheLib', defaultValue: false, description: 'Do you want to build cache-lib?')
        booleanParam(name: 'buildCommonDtos', defaultValue: false, description: 'Do you want to build common-dtos?')
        booleanParam(name: 'buildConverters', defaultValue: false, description: 'Do you want to build converters?')
        booleanParam(name: 'buildHealthchecks', defaultValue: false, description: 'Do you want to build healthchecks?')
        booleanParam(name: 'buildNonFunctionalLib', defaultValue: false, description: 'Do you want to build non-functional-lib?')
        booleanParam(name: 'buildProtoFiles', defaultValue: false, description: 'Do you want to build proto-files?')
        booleanParam(name: 'buildSendingLib', defaultValue: false, description: 'Do you want to build sending-lib?')
    }
    stages {
        stage('cache-lib') {
            when {
                allOf {
                    branch comparator: 'REGEXP', pattern: 'main'
                    // "glob" pattern
                    anyOf {
                        equals expected: true, actual: params.buildCacheLib
                        changeset 'cache-lib/**'
                    }
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
                    anyOf {
                        equals expected: true, actual: params.buildCommonDtos
                        changeset 'common-dtos/**'
                    }
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
                    anyOf {
                        equals expected: true, actual: params.buildConverters
                        changeset 'converters/**'
                    }
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
                    anyOf {
                        equals expected: true, actual: params.buildHealthchecks
                        changeset 'healthchecks/**'
                    }
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
                    anyOf {
                        equals expected: true, actual: params.buildNonFunctionalLib
                        changeset 'non-functional-lib/**'
                    }
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
                    anyOf {
                        equals expected: true, actual: params.buildProtoFiles
                        changeset 'proto-files/**'
                    }
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
                    anyOf {
                        equals expected: true, actual: params.buildSendingLib
                        changeset 'sending-lib/**'
                    }
                }
            }
            steps {
                build job: "shared-libs-sending-lib/${BRANCH_NAME}", wait: false
            }
        }
    }
}
