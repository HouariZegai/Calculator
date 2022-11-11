pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                def Maven_Home = tool name: 'Apache Maven 3.6.0', type: 'maven'
                sh "${Maven_Home}/usr/share/maven -B -DskipTests clean package"
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