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
                    mvn install
                '''
            }
        }

        stage('sonar') {
            steps {
                withSonarQubeEnv(installationName: 'sonarqube-local') {
                    sh '''
                        echo "Performing sonar"
                        mvn sonar:sonar
                    '''
                }
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
