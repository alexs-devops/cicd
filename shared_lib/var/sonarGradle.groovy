def call(String projectKey) {
    script {
        // Check if the 'sonarqube' task is available in build.gradle
        def hasSonarTask = sh(script: "./gradlew tasks --all | grep -q '^sonarqube'", returnStatus: true) == 0

        if (hasSonarTask) {
            echo "Running SonarQube for project: ${projectKey}..."
            withSonarQubeEnv('SonarQube') {
                sh "./gradlew sonarqube -Dsonar.projectKey=${projectKey}"
            }
        } else {
            echo "SonarQube task not defined in build.gradle. Skipping SonarQube analysis."
        }
    }
}