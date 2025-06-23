def call(Map config = [:]) {
    def projectKey = config.projectKey ?: error("projectKey is required")
    def projectName = config.projectName ?: projectKey
    def sonarHost = config.sonarHost ?: 'http://localhost:9000'
    def sonarToken = config.sonarToken ?: error("sonarToken (secret text credential ID) is required")
    def sourceDir = config.sourceDir ?: '.'

    stage('SonarQube Analysis') {
    withCredentials([string(credentialsId: sonarToken, variable: 'SONAR_TOKEN')]) {
        withEnv([
            "SONAR_SCANNER_OPTS=-Djava.io.tmpdir=${env.WORKSPACE}/.sonar-temp"
        ]) {
            bat """
                sonar-scanner.bat ^
                    -Dsonar.projectKey=${projectKey} ^
                    -Dsonar.projectName=${projectName} ^
                    -Dsonar.sources=${sourceDir} ^
                    -Dsonar.host.url=${sonarHost} ^
                    -Dsonar.login=%SONAR_TOKEN%
            """
        }
    }
}
}
