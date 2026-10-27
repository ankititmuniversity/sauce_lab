pipeline {
    agent any
    
    parameters {
        choice(name: 'ENV', choices: ['sit', 'uat', 'prod'], description: 'Select environment to run tests')
        choice(name: 'EXECUTION', choices: ['local', 'remote'], description: 'Select execution mode')
    }

    tools {
        maven 'MAVEN_HOME'
        jdk 'JAVA_HOME'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            when {
                not { expression { fileExists('target/surefire-reports/testng-failed.xml') } }
            }
            steps {
                echo "Running full suite"
                bat "mvn clean test -DsuiteXmlFile=testng.xml -Dheadless=true -Denv=${params.ENV} -Dexecution=${params.EXECUTION}"
            }
        }

        stage('Rerun Failed Tests') {
            when {
                expression { fileExists('target/surefire-reports/testng-failed.xml') }
            }
            steps {
                echo "Re-running failed tests only"
                bat "mvn test -DsuiteXmlFile=target/surefire-reports/testng-failed.xml -Dheadless=true -Denv=${params.ENV} -Dexecution=${params.EXECUTION}"
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
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent_Report'
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '''
                reports/**,
                target/surefire-reports/**,
                test-output/**,
                logs/**
            ''', allowEmptyArchive: true

            cleanWs()
        }

        success {
            echo "Pipeline Succeeded!"
        }

        failure {
            echo "Pipeline Failed! Check the reports."
        }
    }
}
