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

    environment {
        NOTIFY_USERS = 'vxoweiwei@gmail.com'
    }

    parameters {
        booleanParam(name: 'SONAR_QUALITY_GATE', defaultValue: true, description: 'Enable overall code quality check')
        string(name: 'EMAIL_LIST', defaultValue: 'vxoweiwei@gmail.com', description: 'Email notifications to')
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
            mail to: "${params.EMAIL_LIST}", subject: "${env.PROJECT_NAME} - Build # ${env.BUILD_NUMBER} - ${env.BUILD_STATUS}!", body: "${env.PROJECT_NAME} - Build # ${env.BUILD_NUMBER} - ${env.BUILD_STATUS}:\n Check console output at ${env.BUILD_URL} to view the results."
        }
    }
}
