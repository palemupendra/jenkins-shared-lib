def call(Map config = [:]) {
    def projectKey = config.projectKey ?: error("projectKey is required")
    def projectName = config.projectName ?: projectKey
    def sonarHost = config.sonarHost ?: 'http://localhost:9000'
    def sonarToken = config.sonarToken ?: error("sonarToken is required")
    def sourceDir = config.sourceDir ?: '.'
    def scannerPath = config.scannerPath ?: 'C:\\Temp\\sonar-scanner-5.0.1.3006-windows\\bin\\sonar-scanner.bat'

    stage('SonarQube Analysis') {
        withCredentials([string(credentialsId: sonarToken, variable: 'SONAR_TOKEN')]) {
            withEnv([
                "SONAR_SCANNER_OPTS=-Djava.io.tmpdir=${env.WORKSPACE}\\.sonar-temp"
            ]) {
                bat "\"${scannerPath}\" -Dsonar.projectKey=${projectKey} -Dsonar.projectName=${projectName} -Dsonar.sources=${sourceDir} -Dsonar.host.url=${sonarHost} -Dsonar.login=%SONAR_TOKEN%"
            }
        }
    }
}
