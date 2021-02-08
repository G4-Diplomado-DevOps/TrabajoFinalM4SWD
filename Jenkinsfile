pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh "mvn clean test -e"
            }
        }

/*        stage('test') {
            steps {
                sh "/usr/local/opt/maven/bin/mvn compile"
            }
        }

        stage('test2') {
            steps {
                sh '''
                    /usr/local/opt/maven/bin/mvn test
                '''
            }
        }
*/    }
}

