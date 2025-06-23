@Library('jenkins-shared-lib') _

pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                runGitCheckout(true) // or false if you don't want env vars
            }
        }

        stage('Build') {
            steps {
                echo "Do your build stuff here..."
                echo "Branch: ${env.GIT_BRANCH_NAME}"
                echo "Commit: ${env.GIT_COMMIT_ID}"
            }
        }
         stage('Sonar Analysis') {
            steps {
                runSonarAnalysis(
                    projectKey: 'myproject',
    projectName: 'My Project',
    sonarHost: 'http://localhost:9000',
    sonarToken: 'sonar-token-id',
    sourceDir: '.',
    scannerPath: 'C:\\Temp\\sonar-scanner-5.0.1.3006-windows\\bin\\sonar-scanner.bat'                  // Optional, default is '.'
                )
            }
        }
    }
}
