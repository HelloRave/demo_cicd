pipeline {
    agent any

    tools {
        maven 'maven 3.9.8'
        jdk 'OpenJDK17'
    }

    stages {
        stage('clean') {
            sh '''
                echo "Start clean"
                mvn clean
            '''
        }

        stage('test') {
            sh '''
                echo "Start test"
                mvn test
            '''
        }

        stage('build') {
            sh ''''
                echo "Start build"
                mvn install -DskipTests
            '''
        }
    }
}
