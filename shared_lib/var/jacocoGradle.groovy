def call() {
    script {
        echo 'Running JaCoCo...'
        sh './gradlew clean jacocoTestReport'

        // Publish JaCoCo HTML report to Jenkins
        publishHTML([
            reportDir: 'build/reports/jacoco/test/html',
            reportFiles: 'index.html',
            reportName: 'JaCoCo Test Coverage Report',
            keepAll: true,
            alwaysLinkToLastBuild: true,
            allowMissing: false
        ])

        // Archive JaCoCo XML report
        archiveArtifacts artifacts: 'build/reports/jacoco/test/jacocoTestReport.xml', allowEmptyArchive: true
    }