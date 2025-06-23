def call(Map config = [:]) {
    def projectKey = config.projectKey ?: error("projectKey is required")
    def projectName = config.projectName ?: projectKey
    def sonarHost = config.sonarHost ?: 'http://localhost:9000'
    def sonarToken = config.sonarToken ?: error("sonarToken (secret text credential ID) is required")
    def sourceDir = config.sourceDir ?: '.'

stage('SonarQube Analysis') {
    withCredentials([string(credentialsId: 'sonar-token-id', variable: 'SONAR_TOKEN')]) {
        withEnv([
            "SONAR_SCANNER_OPTS=-Djava.io.tmpdir=${env.WORKSPACE}\\.sonar-temp"
        ]) {
            bat """
                C:\\SonarScanner\\bin\\sonar-scanner.bat ^
                    -Dsonar.projectKey=myproject ^
                    -Dsonar.projectName=myproject ^
                    -Dsonar.sources=. ^
                    -Dsonar.host.url=http://localhost:9000 ^
                    -Dsonar.login=%SONAR_TOKEN%
            """
        }
    }
}

}
