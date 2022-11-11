pipeline {
    agent any
    
    stages {
        stage('com'){
            def mvnHome = tool name: 'Apache Maven 3.6.0', type: 'maven'
            sh "${mvnHome}/bin/mvn -B -DskipTests clean package"
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
