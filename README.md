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

============================================================================================================================================================================================================
