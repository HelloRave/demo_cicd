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
                withSonarQubeEnv(installationName: 'sonarqube-local', credentialsId: 'sonarqube-token') {
                    sh '''
                        echo "Performing sonar"
                        mvn sonar:sonar
                    '''
                }

                def qualitygate = waitForQualityGate()
                if (qualitygate.status != "OK") {
                    error "Pipeline aborted due to quality gate coverage failure: ${qualitygate.status}"
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
