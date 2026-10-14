pipeline {
    agent {
        docker {
            image 'maven:3.9.4-eclipse-temurin-17'
            args '-v /root/.m2:/root/.m2'
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
            archiveArtifacts artifacts: 'reports/ExtentReport/**, target/allure-results/**, test-output/**, logs/**',
                             allowEmptyArchive: true
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