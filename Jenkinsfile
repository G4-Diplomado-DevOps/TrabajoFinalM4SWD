pipeline {
    agent any

    stages {
        stage('Install newman') {
            steps {
                sh "npm install -g newman"
            }
        }

        stage('test JUnit') {
            steps {
                sh "mvn clean test -e"
            }
        }

        stage('test Postman') {
            steps {
                sh '''
                    newman run Test-WebServices.postman_collection.json
                '''
            }
        }
    }
}

