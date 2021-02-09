# Cybersec
- This project in a global way consists in highlighting the different security vulnerabilities that an application can have
- We built a small project to shed light on the security flaw
- I have two codes, one secure and the other insecure
    * The secure code is on the *main* branch
    * The insecure code is on the *code-insecure* branch
- A preview image of this projet
![screenshot](previews/preview1.png)

### How to run this code
- First step:
- Second step:
    * Run the command  java -jar target/secu-0.0.1-SNAPSHOT.jar  
    

### Built With
- HTML, CSS, Bootstrap
- Spring

### The security vulnerabilities that we exploit
- SQL injection (https://www.w3schools.com/sql/sql_injection.asp)
- Clickjacking (https://en.wikipedia.org/wiki/Clickjacking)
- Cross-site scripting(XSS) 
- ...

##We are in the main branch, so the secure branch
###Injection SQL attack 
1) Use predefined functions for request such as getOne, findAll and so on in order to prevent Sql injections.
With these predefined functions in Spring boot, every element entered by the user are transformed in text.
2) One other way to prevent SQL injections is to use prepared request
https://www.journaldev.com/34028/sql-injection-in-java
3) We can also remove special characters for protecting our code from SQL injection by using regex with the objective to
identify special characters and syntaxes SQL. I can suggest as regex \b(select)\b)|(\b(from)\b)|\*|\'|(\b(AND)\b)|\= 

Inside my secure code, I chose the option 1 and I think it's more simple and optimized than the other methods.

#####How to test this part of the security of my code?

###Clickjacking attack 

## Author

👤 **Daniel Jordan Sipe Tchawou**

- Github: [tchawou-daniel](https://github.com/tchawou-daniel)
- Linkedin: [linkedin](https://linkedin.com/in/daniel-jordan-sipe-tchawou)
