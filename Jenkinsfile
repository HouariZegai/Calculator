pipeline {
    agent any

    stages {
        stage ('Compile Stage') {

            steps {
               
                    sh 'mvn clean compile'
                    echo 'maven clean completed'
            }
        }

        stage ('Testing Stage') {

            steps {
                
                    sh 'mvn test'
                    echo 'mvn test completed'
            }
        }


//         stage ('Deployment Stage') {
//             steps {
               
//                     sh 'mvn deploy'
//                     echo 'mvn deploy completed'
                
//             }
//         }
//     }
// }