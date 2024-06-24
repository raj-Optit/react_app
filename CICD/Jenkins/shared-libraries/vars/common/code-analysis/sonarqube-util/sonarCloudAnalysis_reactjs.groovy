def performSonarCloudAnalysisforReactjs(String projectKey, String organization, String sourcesDir, String sonarCloudTokenId) {
    def scannerHome = tool 'mysonar' // Use the name of your SonarQube installation
    println "Using SonarQube scanner from: ${scannerHome}"
    def sonarCloudToken = credentials(sonarCloudTokenId)

    // Debug output
    println "Project Key: ${projectKey}"
    println "Organization: ${organization}"
    println "Sources Directory: ${sourcesDir}"
    println "SonarCloud Token: ${sonarCloudToken}"

    // Run the SonarCloud analysis
    withSonarQubeEnv('SonarCloud') {
        sh """
            ${scannerHome}/bin/sonar-scanner \
            -Dsonar.projectKey=${projectKey} \
            -Dsonar.organization=${organization} \
            -Dsonar.sources=${sourcesDir} \
            -Dsonar.host.url=https://sonarcloud.io \
            -Dsonar.login=${sonarCloudToken} \
            -X
        """
    }
}

return this
