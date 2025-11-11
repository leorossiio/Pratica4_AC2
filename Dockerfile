# Usa uma imagem Java 17 leve e estável
FROM eclipse-temurin:17-jdk-alpine

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR gerado pelo Maven para dentro do container
# O nome do JAR vem do seu pom.xml: <artifactId>pratica4_ac2</artifactId>
COPY target/pratica4_ac2-0.0.1-SNAPSHOT.jar /app/app.jar

# Expõe a porta que a aplicação usa (o padrão do Spring é 8080)
EXPOSE 8080

# Comando padrão de inicialização da aplicação
CMD ["java", "-jar", "app.jar"]