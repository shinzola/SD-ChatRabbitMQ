FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# ✅ COMANDO CORRIGIDO para copiar o JAR correto e final
COPY target/producer-1.0-SNAPSHOT.jar producer.jar

ENTRYPOINT ["java", "-jar", "producer.jar"]