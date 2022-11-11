pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                
                    sh 'clean insall'
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
                sh './jenkins/scripts/deliver.sh'
                echo "Deliver stage compleed."
            }
        }
    }
}