FROM java:8-jre

EXPOSE 8090

ADD demo-payment-system-1.0-SNAPSHOT.jar app/
CMD ["java", "-jar", "/app/demo-payment-system-1.0-SNAPSHOT.jar"]