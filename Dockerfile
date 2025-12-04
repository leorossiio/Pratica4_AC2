FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/pratica4_ac2-0.0.1-SNAPSHOT.jar /app/app.jar

# --- INÍCIO HARDENING ---
# Cria um grupo e um usuário 'spring' para não rodar como root
# Referência: Documento DevSecOps Seção 6.2
RUN addgroup -S spring && adduser -S spring -G spring

# Define que o container usará este usuário daqui para baixo
USER spring
# --- FIM HARDENING ---

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]