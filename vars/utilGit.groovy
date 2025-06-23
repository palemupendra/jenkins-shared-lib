def setEnvVarsFromGitProperties() {
    if (isUnix()) {
        def commit = sh(script: "git rev-parse HEAD", returnStdout: true).trim()
        def branch = sh(script: "git rev-parse --abbrev-ref HEAD", returnStdout: true).trim()

        env.GIT_COMMIT_ID = commit
        env.GIT_BRANCH_NAME = branch
    } else {
        def commit = bat(script: "git rev-parse HEAD", returnStdout: true).trim()
        def branch = bat(script: "git rev-parse --abbrev-ref HEAD", returnStdout: true).trim()

        env.GIT_COMMIT_ID = commit
        env.GIT_BRANCH_NAME = branch
    }

    echo "Branch: ${env.GIT_BRANCH_NAME}, Commit: ${env.GIT_COMMIT_ID}"
}
