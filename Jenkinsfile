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
                      def version = sh("gradlew -q :${subModuleName}:printVersion")
                      [subModuleName: version - '-SNAPSHOT' - '-dirty']
                  }
                env.NEXT_VERSIONS = env.CURRENT_VERSIONS.collectEntries { subModuleName, version ->
                    // TODO: increment patch version (1.5.15 -> 1.5.16)
                    [(subModuleName), incrementVersion(version, false, false)]
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
               } catch(e) {
                  sh(script: "git tag -d $tagName")
                  return
               }
               sh(script: "git push origin --tags")

               // 3. if everything is fine - push tag         - only if on "main" branch
               //     else delete tag
               // 4. ./gradlew publish
               //    publish should be configured for our nexus
               /* password = credentials('nexus-password-name')
               def rsaKey = credentials('jenkins-dev-event-receiver-id-rsa') */
           }
         }
       }
    }
}
def incrementVersion(String version, boolean isMinor, boolean isMajor) {
  def parts = version.split('\\.');
  if (isMajor) {
      def major = parts[0] as int
      return String.valueOf(major+1).concat('.0.0')
  }
  if(isMinor) {
      def minor = parts[1] as int
      return parts[0]+'.'+String.valueOf(minor+1)+'.0'
  }
  def patch = parts[2] as int
  return parts[0]+'.'+parts[1]+'.'+String.valueOf(patch+1)
}