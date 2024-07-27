def NOTIFY_USERS = 'vxoweiwei@gmail.com'

pipeline {
    agent any

    tools {
        maven 'maven 3.9.8'
        jdk 'OpenJDK17'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '1'))
        disableConcurrentBuilds(abortPrevious: true)
    }

    parameters {
        booleanParam(name: 'SONAR_QUALITY_GATE', defaultValue: true, description: 'Enable overall code quality check')
        string(name: 'EMAIL_LIST', defaultValue: $NOTIFY_USERS, description: 'Email notifications to')
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

                script {
                    def qualitygate = waitForQualityGate()
                    if (qualitygate.status != 'OK') {
                        error "Pipeline aborted due to quality gate coverage failure: ${qualitygate.status}"
                    }
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

    post {
        always {
            mail to: ${params.EMAIL_LIST}, subject: 'Jenkins build status'
        }
    }
}
