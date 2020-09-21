pipeline {
    agent any
    tools {
        maven 'maven'
        allure 'allure'
    }
    stages {
        stage('clone repository') {
            steps {
                deleteDir()
                git branch: 'master', url: 'https://github.com/manunia/SimbirSoftSDET.git'
            }
        }
        stage('run tests') {
            steps {
                sh "mvn clean test"
        }
        }
        stage('generate allure report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }
}