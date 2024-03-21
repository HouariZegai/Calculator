# githubnotes
-> Git/Github ---> it is source code management tool which helps you to manage the application code.
-> Git is local repository which is on you local machine.
-> Github is centralised repository were you need to login using username/passwd.

-> git init -> which helps to convert normal directory to git directory.(which initilise the git congiguration).

-> Git clone -> were we can download entire repository from github to local git
     git clone <github url> (url -> github repo url)
     cd <reponame>  -> go inside directory
     vi filename    -> to create new file (esc->shift:->wq)
     git add .      -> . indicates all files
     git commit -m "message" -> (-m is message)
     git push origin branchname  ->push code to github.
     git status  -> to see the status before and after command exection for both add&commit.

     git pull origin branchname  ->pull code into local git

-> git branching ---> Branching will helps developers to work parallely or samintaniously with out any dependency on each other.

     git branch <bname> --> it will create branch 
     git checkout <bname>  -> switch from one to another branch

     git checkout -b <bname>  -> create and switch to that branch
     git branch -d <bname>    -> delete branch (-d delete)
     git branch    -> list the branches which are on local git
     git branch -a  -> it will list branches from both git and github. (-a   all branches)

-> git log  --> commit history display
   git log --oneline  shows commit history with less content

-> git diff <bname> <bname>     -> it will show diff b/w two branches
   git diff <commitid> <commitid>  -> it will show diff b/w two commit id's
   git show <commitid>   -> to see details info for commit id   

-> git merge --> combine two diff branches into single branch.
                  --> github->pullrequest->create pullrequest->merge request
   git checkout main
   git merge <main> <test>    -> it is merging test to main branch.  

-> git stash  --> to store current working directory temporarly 
            git stash      -> save the current work dir to stash
            git stash list -> show the list of saved items
            git stash apply -> to get back the save curent dir
            git stash drop  -> delete the saved items from stash

-> .gitignore  --> .gitignore file will helps to skip/avoid the unwanted files while pushing into github.
    vi .girignore --> *.log

-> git reset --> to undo the changes/// -> move from current state to previous state.

   git reset <filename> 

-> Setup git in Linux VM
    sudo yum install git -y
    ssh-keygen -> enter -> enter-> enter 
    to get key -> cat /home/ec2-user/.ssh/id_rsa.pub
    copy the key to github account in settings -> SSH option
    git clone <repo SSH URL> -> yes -> enter ---> it will start clone.

=====================================================================================================================================================================================
    
Maven Tool:
-> Maven is build automation tool
-> using maven we are goint to build the application code written by developers
-> After build it will generate target folder and .jar file inside init (target/***.jar)
-> maven is having lifecycle which contains diff stages/phases
     -> generate resource - Generate required de[pendencies to build application code using maven.
     -> Compile code -- convert code into binary language (machine readable)
     -> Unit test --- Maven will test internally diff test cases
     -> Package ---- Creating .jar file from the code (copying all the required code into jar)
     -> install ----- Install the .jar on maven server
     -> Deploy ----- Deploy is ntg but upload/deploy the dependencies and .jar files to maven repository.
     -> Clean ----- Claen the existing .jar file and recreate new .jar file.
-> When we download and install maven
     -> .M2 folder ---- were it containes entire configuration of maven.
         settings.xml ---- Maven repo URL then username/password.
         pom.xml ---- when we execute maven commnd will simply follow the pom.xml file
     -> Using single command we can execute the entire maven lifecycle
         mvn clean install
-> Download and install maven in windows/Linux
Windows:
    -> https://phoenixnap.com/kb/install-maven-windows (Installation steps)
    -> https://maven.apache.org/download.cgi (download maven package)
Linux:
   -> Sudo yum install maven -y
   -> maven -version

-> Make sure execute maven command were the POM file is.
Sample Repo: https://github.com/Renukadema/Calculator.git 

==================================================================================================================================================================
What is Docker?
Docker is light weight container, were you can build and run your application on docker.
Docker is very fast in build and running application.
Docker will only support single application deployment.

-> hardware-==> kernel OS----->Docker engine---->Application
-> Install docker on linux machine
    * sudo yum install docker -y  (Install docker on our VM).
    * sudo systemctl start docker  (will start the docker service).
    * sudo systemctl enable docker (if we stop the VM docker service also will stop and once VM is start automatically it will start the docker service as well).
    * sudo systemctl restart docker (to restart the docker service).

-> Docker engine ---> which containes docker config and which heps to run the docker service
-> Docker Repository --> were you can store the docker images (here we can do pull/push mechanism)
-> Docker images -> we can build docker image using docker file 
-> Dockerfile ---> Group of instructions

Docker Commands:
-> **Docker info** ---- we can see the config/details about docker
-> **Docker images** ---- to see list of docker images
-> **Docker rmi <imagename>** --- to delete specific docker image
-> **Docker build -it <imagename> .** ----to build docker image from dockerfile
-> docker ps --- it will show the running containers
-> docker ps -a -- it will show both stop and running containers.
-> docker rm <containername> --- delete docker container (make sure container should be in stop state)
-> docker run -it --name <containername> <imagename> /bin/bash
-> docker commit <containerid> ---- from this we can build docker image
-> docker logs -f <containername> ----- to see container logs
-> docker exec -it <containername> /bin/bash  ------ to login into container
-> docker stats <containername> ---- you can see the resource utilization cpu/ram/memory
-> docker system prune ---- unused images/containers will be deleted
-> docker run -it --name <containername> --privileged=true --volumes-from <oldcontainer> <images> /bin/bash 
========================================================================================================================================================

Docker ----> 
 
-> Docker is containerised tool which helps to build and run your application as a container.
-> Using Docker we can build our application as a docker image.
-> Docker is very fast in building and running our application compare to other tools.

-> Docker Structure 
    -> Hardware----Kernel OS---Docker Engine---Applications.

-> Docker will helps to run only single container at a time.

-> DockerFile ---             Group of instructions to build docker image.
-> Docker Engine ----         which contains entire docker configuration
-> Docker Repository ---      Were we can store our docker images by using (pull/push) commands.
                              We have both public and private repository's
-> Docker Service ---         Which helps you to run Docker and execute the docker commands
-> Docker Container ---       Container is nothing but a Software were we can easily deploy.

Install Docker:  (Linux)                  -> Enterprise version (Practice)
->  sudo yum install docker -y             -> it will install docker service on Linux VM.
    sudo systemctl start docker           -> to start docker service on VM
    sudo systemctl enable docker          -> To start docker service automatically when th VM is start and stop
    sudo systemctl status docker          -> to check the status of docker 
    sudo systemctl restart docker         -> to restart docker service
    sudo systemctl stop docker            -> to stop the docker service 

-> Docker Commands:

docker images                                    -> to see list of docker images
docker ps                                        -> to see running containers
docker ps -a                                     -> to see both stop/running containers
docker rmi <imagename/imageID>                   -> to delete the docker image (rmi --- remove image)
docker rm <containerID>                          -> to delete container (make sure we can delete only stopped containers)
docker start <containerID>                       -> to start docker container
docker stop <continerID>                         -> to stop container
docker pause/unpause <containerID>               -> to pause/unpause container
docker build -t <imagename> .                    -> to build docker image from dockerfile (-t=terminal, . path of dockerfile)
docker create image  -> container creation
docker run -it --name <newcontainername>  <image> /bin/bash   (-it= interactive terminal) -> to create container from docker image
docker commit <containerID>  <newimagename>      -> to create image from container
docker exec -it <containername> /bin/bash        -> to login inside container
docker logs -f <containerID>                     -> to see the container logs
docker system prune                              -> to remove unused images and containers
docker images prune -a                           ->it will clean all images
docker info                                      ->to see the docker information installed on system
docker stats <cintainerID>                       -> to see container resource (cpu, mm, space....)

============================================================================================================================================
**SonarQube** -> Security tool
-To scan our code in the repository.
-Sonar Qube will helps us to find the bugs, vurnabilities and code related issues.
-Sonar report will be uploaded to Sonar Hub
-Reports will assigned to developers.

->download and install SonarQube

->wget https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-10.4.1.88267.zip?_gl=1*1k01es0*_gcl_au*NDcxMDQ0MTA2LjE3MDQ4NTA0NTg.*_ga*MzQ3NDcyODQ5LjE3MDQ4NTA0NTg.*_ga_9JZ0GZ5TC6*MTcxMTAwMzk3Ny45LjEuMTcxMTAwNDAwMS4zNi4wLjA.

->sudo mv sonarqube-10.4.1.88267.zip\?_gl\=1\*1k01es0\*_gcl_au\*NDcxMDQ0MTA2LjE3MDQ4NTA0NTg.\*_ga\*MzQ3NDcyODQ5LjE3MDQ4NTA0NTg.\*_ga_9JZ0GZ5TC6\*MTcxMTAwMzk3Ny45LjEuMTcxMTAwNDAwMS4zNi4wLjA. sonar.zip

-> sudo unzip sonar.zip

-> cd sonarqube-10.4.1.88267/bin/linux-x86-64/
   -> ll
   ->  sudo ./sonar.sh start
   ->  sudo yum install maven -y (to setup java on this)
   ->  sudo ./sonar.sh start
   ->  sudo ./sonar.sh stop
   ->  sudo ./sonar.sh start
Use IP Address of VM with PORT number
http://IPAddress:9000/         (9000 --- default port for Sonar)

==============================================================================================================================
**Helm**
-> Using helm we are creating packages (.tgz) 
-> Using helm we can install, upgrade, and deploy our application on Kubernetes Cluster.
-> Helm package/helm chart both are same.
-> Helm Folder Structure 
    -> helm -> whatsapp -> chart.yaml
                        -> Values.yaml
                        -> templates folder -> service.yaml
                                               deployment.yaml
                                               database.yaml
                                               secrets

-> Helm create whatsapp (it will create hel folder structure)
-> helm lint whatsapp   (to verify the folder structure or syntax for helm)
-> helm package whatsapp (it will generate .tgz file)
-> helm install whatsapp whatsapp-1.0.0.tgz (it will install chart on our cluster for first time)
-> helm upgrade --install whatsapp whatsapp-1.0.0.tgz (upgrade/second time installation od same chart with new version)
-> helm ls
-> helm uninstall whatsapp (delete/uninstall the chart from cluster).

Install helm on linux VM:
- sudo curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3
- sudo chmod 700 get_helm.sh
- sudo ./get_helm.sh



