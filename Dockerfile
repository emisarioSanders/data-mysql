FROM adoptopenjdk/openjdk11:ubi
COPY target/data-mysql-0.0.1.jar data-mysql-0.0.1.jar
ENTRYPOINT ["java","-jar","/data-mysql-0.0.1.jar"]