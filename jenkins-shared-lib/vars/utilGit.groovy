def setEnvVarsFromGitProperties() {
    // You can customize this logic to fetch and set values
    def gitCommit = sh(script: "git rev-parse HEAD", returnStdout: true).trim()
    def gitBranch = sh(script: "git rev-parse --abbrev-ref HEAD", returnStdout: true).trim()

    env.GIT_COMMIT_ID = gitCommit
    env.GIT_BRANCH_NAME = gitBranch

    echo "Git Commit: ${env.GIT_COMMIT_ID}"
    echo "Git Branch: ${env.GIT_BRANCH_NAME}"
}
