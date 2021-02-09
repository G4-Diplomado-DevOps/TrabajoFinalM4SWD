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
        stage('test Postman') {
            steps {
                sh '''
		wget https://github.com/G4-Diplomado-DevOps/TrabajoFinalM4Postman/blob/main/test-postman-ms.json
		mkdir drivers
		mkdir drivers/chromedriver
		cd drivers/chromedriver
		
		'''
	    }
	}
    }
}

