pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                   sh 'mvn -B -DskipTests clean package'
                    echo "Maven Build is compleed"
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
                echo "Maven stage compleed."
            }
            
        }
        stage('Deliver') {
            steps {
                bat './jenkins/scripts/deliver'
                echo "Deliver stage compleed."
            }
        }
    }
}
