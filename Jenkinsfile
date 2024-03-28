pipeline {
    agent any

   stages {
        stage('code checkout') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/Renukadema/Calculator.git'
            }
        }
        stage('Maven Build') {
            steps {
                
                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'sonar-token') {
                    sh "mvn clean verify sonar:sonar -Dsonar.projectKey=Calculator -Dsonar.projectName='Calculator'"
                }    
            } 
        }
        stage('Build docker') {
            steps {
                //sh "sudo docker build -t calculator . --file dockerfile"
                sh '''#!/bin/bash
                docker build -t calculator . --file Dockerfile
                '''
            }
        }
        stage ("Push to ECR") {
            steps {
               sh "aws ecr get-login-password --region ap-south-1 | sudo docker login --username AWS --password-stdin 261393467661.dkr.ecr.ap-south-1.amazonaws.com"
               sh "docker tag calculator 261393467661.dkr.ecr.ap-south-1.amazonaws.com/calculator"
               //sh "docker push 261393467661.dkr.ecr.ap-south-1.amazonaws.com/calculator"
            }
        }
        stage('Helm Package') {
            steps {
                sh '''#!/bin/bash
                helm create calculator
                helm package calculator
            '''
            }
        }
        stage('AWS EKS Authentication') {
            steps {
                withAWS(region: 'ap-south-1', credentials: 'aws-credentials') {
                    sh "aws eks --region ap-south-1 update-kubeconfig --name awseks"
                }
            }
        }
        stage('Kubernetes Setup') {
            steps {
                // Use Kubernetes plugin to set up the connection to EKS
                withKubeConfig(credentialsId: 'K8S', clusterName: 'awseks', serverUrl: 'https://B8D3E5A4AEA99EA3C2C5693199C1ADF2.gr7.ap-south-1.eks.amazonaws.com') {
                }
            }
        }
        stage('Install Helm Chart') {
            steps {
                // Install Helm and initialize Helm
                sh 'helm lint calculator'
                
                // Add Helm repository if necessary
                sh 'helm repo add stable https://charts.helm.sh/stable'
                
                // Install Helm chart
                //sh 'helm install calculator calculator --namespace=default'
            }
        }
    }
}
