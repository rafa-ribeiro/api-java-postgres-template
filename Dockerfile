# Usa a imagem oficial do OpenJDK 17
FROM openjdk:17-jdk-alpine

# Define a versão do Gradle
ARG GRADLE_VERSION=7.4

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia os arquivos Gradle necessários
COPY build.gradle .
COPY settings.gradle .
COPY gradle/ ./gradle/


# Copia o resto dos arquivos do projeto
COPY src ./src

# Instala o Gradle
RUN apk add --no-cache wget unzip \
    && wget -q --no-check-certificate "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" \
    && unzip -q "gradle-${GRADLE_VERSION}-bin.zip" \
    && rm "gradle-${GRADLE_VERSION}-bin.zip" \
    && ln -s "/app/gradle-${GRADLE_VERSION}/bin/gradle" /usr/bin/gradle

# Constrói o projeto usando o Gradle
RUN gradle build

# Configuração do ambiente para a aplicação Java
ENV PORT=8080
EXPOSE $PORT

# Comando para iniciar a aplicação Java
CMD ["java", "-jar", "build/libs/meli-0.0.1-SNAPSHOT.jar"]
