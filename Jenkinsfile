pipeline {
    agent {
        docker {
            image 'maven:3.9.4-eclipse-temurin-21'
        }
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Allure Report') {
            steps {
                allure includeProperties: false,
                       results: [[path: 'allure-results/']]
            }
        }

        stage('Extent Report') {
            steps {
                publishHTML([
                    reportDir: 'reports/ExtentReport',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Report',
                    allowMissing: false,
                    keepAll: true,
                    alwaysLinkToLastBuild: true
                ])
            }
        }
    }

    post {
        always {
            // Ensure workspace context is available
            archiveArtifacts artifacts: '''
                reports/ExtentReport/**,
                target/allure-results/**,
                test-output/**,
                logs/**
            ''', allowEmptyArchive: true

            cleanWs()
        }
        success {
            echo 'Pipeline Succeeded!'
        }
        failure {
            echo 'Pipeline Failed! Check reports.'
        }
    }
}
