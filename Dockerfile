FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

WORKDIR /usr/share/udemy

ADD target/selenium-docker.jar selenium-docker.jar
ADD target/selenium-docker-tests.jar selenium-docker-tests.jar
ADD target/libs libs

ADD book-flight-module.xml book-flight-module.xml
ADD search-module.xml search-module.xml

#ADD healthcheck a2e0ae3192d9428c894560835d75317e
ADD healthcheck.sh healthcheck.sh

#BROWSER
#HUB_HOST
#MODULE

#ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DBROWSER=$BROWSER -DHUB_HOST=$HUB_HOST org.testng.TESTNG $MODULE
ENTRYPOINT sh healthcheck.sh