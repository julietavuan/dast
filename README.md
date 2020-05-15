# dast
DAST tool with ZAP

Use docker-compose.yml to run de app.
For this challenge I add the JAR files of zap_attack and dast to make the buil easier, 
a better way could be a script that creates the JAR that runs before docker-compose up .
For now, we have this way :) .

You can scan an URL by use the 
POST request like:
POST http://localhost:9091/dast/scanning 
body: 
{https://www.randomstorm.com/images/tools/dvwa.png}

and to see the results of the scanning use the
GET request like:
GET http://localhost:9091/dast/spiderings/{scanId}/results

