def call(Boolean loadDefaultEnv = true) {
    pipelineLogger("Cloning Code...")

    // Default Git checkout
    checkout scm

    // Load Git environment variables if needed
    if (loadDefaultEnv) {
        utilGit.setEnvVarsFromGitProperties()
    }
}
