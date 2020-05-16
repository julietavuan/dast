# dast
DAST tool with ZAP

Run `docker-compose up` to execute the app. I added the JAR files for zap_attack and dast to make it easier/faster to build zap and dast images.
You can also use de star-dast.sh to run:
- mvn clean
- create images
- run docker-compose up
The problem is that sometimes it does not work so good. So I recomend to use the JARS and comment the mvn clean command.

You can scan an URL by use the 
POST request like:
POST http://localhost:9091/dast/scanning 
body: 
{https://www.randomstorm.com/images/tools/dvwa.png}

and to see the results of the scanning use the
GET request like:
GET http://localhost:9091/dast/spiderings/{scanId}/results

