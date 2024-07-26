pipeline {
    agent any

    tools {
        maven 'maven 3.9.8'
        jdk 'OpenJDK17'
    }

    stages {
        stage('clean') {
            steps {
                sh '''
                    echo "Start clean"
                    mvn clean
                '''
            }
        }

        stage('test') {
            steps {
                sh '''
                    echo "Start test"
                    mvn test
                '''
            }
        }

        stage('build') {
            steps {
                sh '''
                    echo "Start build"
                    mvn install -DskipTests
                '''
            }
        }
    }
}
