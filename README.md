# Cybersec
- This project mainly consists in highlighting the different security vulnerabilities than an application may have
- We built a small project to bring the security flaw (s) to light
- I have two codes, one secure and the other insecure
    * The secure code is on the *main* branch
    * The insecure code is on the *code-insecure* branch
- A preview image of this projet
![screenshot](previews/preview1.png)

### How to run this code
- First step(Database) :
    * docker pull mysql:8.0.1
    * docker run --name mysqlsecu -e MYSQL_ROOT_PASSWORD=jordan96 -d mysql:8.0.1
    * docker pull phpmyadmin/phpmyadmin:latest
    * docker run --name phpmyadminsecu -d --link mysqlsecu:db -p 8081:80 phpmyadmin/phpmyadmin
    * You can find the database inside the folder cybersec/database
    * Import the data base (two possibiliies), we can do it through the interface graphically or by the command line
        * Interface graphically : 
            1) Use http://localhost:8081/phpmyadmin so that PHPMyAdmin is displayed
            2) - https://help.dreamhost.com/hc/en-us/articles/214395768-phpMyAdmin-How-to-import-or-restore-a-database-or-table
               - https://waytolearnx.com/2020/01/importer-et-exporter-une-base-de-donnees-mysql.html
        * In command line:
             - docker exec -i mysqlsecu mysql -uroot -pjordan96 tasksdb < /chemin/vers/la base de donnée/tasksdb.sql
- Second step:
    * Inside folder's project (cybersec) you have to run in command line : mvn clean package
    * Run also the command : java -jar task-manager.jar
    * Try this url: localhost:8086 in order to see the program

- Third step: Acces user
    * username: user
    * password: jordan96
  
    
**Inspire links:** 
- https://medium.com/@migueldoctor/run-mysql-phpmyadmin-locally-in-3-steps-using-docker-74eb735fa1fc
- https://stackoverflow.com/questions/43880026/import-data-sql-mysql-docker-container
### Built With
- HTML, CSS, Bootstrap
- Spring
- Mysql
### Tool used
- Regex101(https://regex101.com/) in order to find the correct regex 
### The security vulnerabilities that we exploit
- SQL injection (https://www.w3schools.com/sql/sql_injection.asp)
- Clickjacking (https://en.wikipedia.org/wiki/Clickjacking)
- Cross-site scripting(XSS)  (https://owasp.org/www-community/attacks/xss/)
- ...

## We are in the main branch, so the secure branch
### A) Injection SQL attack 
SQL attacks :
1) Use predefined functions to request such as saveAndFlush in order to prevent Sql injections 
With these predefined functions in Spring boot, every element entered by the user are transformed in text.
2) Another way to prevent SQL injections is to use prepared request 
(https://www.journaldev.com/34028/sql-injection-in-java)
3) We can also remove special characters to protect our code from SQL injection by using regex with the objective to
identify special characters and syntaxes SQL. I can suggest as regex (\b(select)\b|\b(SELECT)\b|(\b(from)\b)|(\b(FROM)\b)|\*|\'|(\b(and)\b)| (\b(AND)\b)|\=|(\b(where)\b)|(\b(WHERE)\b)|(\b(drop)\b)|(\b(DROP)\b)|(\b(1=1)\b)|\=|\;)

- Inside my secure code, I chose the options **1** and **3**.

- In my demo concerning the securisation of this attack bellow you can see when I remove these codes
        task.setDescription(task.getDescription().replaceAll("(\\b(select)\\b|\\b(SELECT)\\b| ....)", ""));
        task.getName().replaceAll("(\\b(select)\\b|\\b(SELECT)....)

 inside MainController even I insert for exemple a syntaxes SQL in my field the predefined function of spingboot(saveAndFlush) transform the data to String. I insert the code with regex for conserving the consistency of my data


### B) Clickjacking attack & XSS & Web Cache Poisoning Attacks & HTTP attack & CSRF
I used **Spring security**(https://spring.io/projects/spring-security) in my code.
The implementation of spring security allow us to secure by default our application against certain attacks such as:
1) Clickjacking: https://en.wikipedia.org/wiki/Clickjacking
2) XSS : https://owasp.org/www-community/attacks/xss/
3) Web Cache Poisoning Attacks : https://link.springer.com/referenceworkentry/10.1007%2F978-1-4419-5906-5_666
4) HTTP attack: https://blog.qualys.com/vulnerabilities-research/2011/11/02/how-to-protect-against-slow-http-attacks
5) CRSF attack: https://portswigger.net/web-security/csrf

**Here is some references about the security by default when we use spring security:** 
- https://www.baeldung.com/spring-security-csrf
- https://spring.io/blog/2013/08/23/spring-security-3-2-0-rc1-highlights-security-headers#content-type-options

# How to test my code concerning the attacks mentioned above ?
### SQL Attacks

Here we can try to insert inside the input field in order to test :
- maths'
- ‘ or 1=1;–
- ‘ or 1=1; drop table t_tasks; 

[Demo SQL Attacks whithout using regex](https://drive.google.com/file/d/1QC3r9tea08yUAb_IkLvHvTMvvv-60oww/view?usp=sharing)

[Demo SQL Attacks whit regex inside the app code](https://drive.google.com/file/d/1F1kHXDPW2d0coZLjuNmIHmwL-5lV9MzA/view?usp=sharing)


**Inspiration link**: 
https://www.softwaretestinghelp.com/sql-injection-how-to-test-application-for-sql-injection-attacks/

### Clickjacking
- Some links which can help ([first link](https://clickjacker.io/test?url=https:%2F%2Fwww.isnov.com%2Fhome%2F), [second link](https://www.lookout.net/test/clickjack.html))
, I could not test this but here are some links which may help to do so
### Web Cache poisoning Attacks
- Some links which can help ([first link](https://blog.detectify.com/2020/07/28/do-you-trust-your-cache-web-cache-poisoning-explained/), [second link](https://portswigger.net/research/practical-web-cache-poisoning))
,I could not test this but here are some links which may help to do so
### Slow HTTP attack 
 - To test this attack we need to install slowhttptest(SlowHTTPTest is a highly configurable tool that simulates some Application Layer Denial of Service attacks.). On Linux we can do that with this command:  sudo apt-get install slowhttptest.
 - You can test the security with this command: 
    *slowhttptest -c 1000 -H -g -o slowhttp -i 10 -r 200 -t GET -u http://localhost:8086/ -x 24 -p 3
 - if service available:   YES
    * if **service available: YES** then the system is secure
 - You can see my test on the picture bellow
    * ![screenshot](previews/test-htttpattack.png)
 - Inspiration links:
   * https://www.youtube.com/watch?v=lNzMIeyLIPM
   * https://tools.kali.org/stress-testing/slowhttptest

### CRSF attack
I think I wasn't able to test this attack but I implemented a code that can help exploiting in case there's a use of sessions or cookies
You can find this through this path /cybersec/src/resources/templates/xss_attack.html, 
 - Inspiration link: https://www.enisa.europa.eu/topics/csirts-in-europe/glossary/cross-site-scripting-xss

### What I think insecure
- The user can change the page through the URL, especially the edit-task page, he can change the task he wants to edit through the URL. 
He just has to change the ID to do that, I don't know at the moment how to secure this part if it's eventually an issue as I think. I didn't have the time to search deeper.
- [Link Demo insecure](previews/https://drive.google.com/file/d/17HqGACgovKW6adGaXcJHCtYIETC2OqtI/view?usp=sharing)


### SECURITY OBJECTIVES
##### Privacy (3/5):
The information should be available only to those with the necessary rights.
In my application, you can see the user must know his username and password in order to see the content

#####  Integrity (4/5): 
The expected data must be those received, without any possible alteration. I tried to conserve the coherence with of my data by using regex and by securing the reception and transmission of information by the application.

#####  Availability (3/5): 
The system must be in optimal operating condition and respond to user requests in a timely and rapid manner.

#####  The traceability (2/5): 
Any trace of any activity must be able to be recorded. Actually, the traceability is not a priority in our system because we didn't finish the implementation of this.

#####  SECURITY FEATURES implemented
- Spring security
- Use of regex to have a minimum of consistency



## Author
**Daniel Jordan Sipe Tchawou**
- Github: [tchawou-daniel](https://github.com/tchawou-daniel)
- Linkedin: [linkedin](https://linkedin.com/in/daniel-jordan-sipe-tchawou)
