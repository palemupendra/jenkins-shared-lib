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
                    projectName: 'myproject',
                    sonarHost: 'http://localhost:9000',
                    sonarToken: 'sqp_cff03050c4b1df3e96eb1b931a6062df2de5f68d',       // Jenkins Secret Text credential ID
                    sourceDir: '.'                       // Optional, default is '.'
                )
            }
        }
    }
}
