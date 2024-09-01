def call() {
    script {
        echo 'Running PMD...'
        sh './gradlew pmd'

        // Publish PMD report to Jenkins
        recordIssues tool: pmdParser(pattern: 'build/reports/pmd.xml')

        // Archive PMD report
        archiveArtifacts artifacts: 'build/reports/pmd.xml', allowEmptyArchive: true
    }
}