def call(String gradleVersion = '7.6.1') {
    pipeline {
        agent any

        stages {
            stage('Install Gradle') {
                steps {
                    script {
                        // Define the Gradle installation URL
                        def gradleUrl = "https://services.gradle.org/distributions/gradle-${gradleVersion}-bin.zip"
                        def gradleDir = "${env.WORKSPACE}/gradle-${gradleVersion}" // or define local dir

                        // Download and unzip Gradle if it doesn't exist
                        if (!fileExists("${gradleDir}/bin/gradle")) {
                            sh """
                                curl -L -o gradle-${gradleVersion}-bin.zip ${gradleUrl}
                                unzip gradle-${gradleVersion}-bin.zip -d ${env.WORKSPACE}
                                rm gradle-${gradleVersion}-bin.zip
                            """
                        } else {
                            echo "Gradle ${gradleVersion} is already installed."
                        }

                        // Set the Gradle path
                        env.PATH = "${gradleDir}/bin:${env.PATH}"
                    }
                }
            }
        }
    }
}