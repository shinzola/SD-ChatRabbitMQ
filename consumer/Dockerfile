# SdChat/consumer/Dockerfile
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# ✅ COMANDO CORRIGIDO para copiar o JAR correto e final
COPY target/consumer-1.0-SNAPSHOT.jar consumer.jar

ENTRYPOINT ["java", "-jar", "consumer.jar"]