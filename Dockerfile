# Используем базовый образ с JDK для сборки приложения
FROM maven:3-openjdk-17 as build

# Копируем остальные файлы проекта
COPY src /home/application/src

# Копируем pom.xml из корневой директории
COPY pom.xml /home/application
USER root

# Выполняем сборку проекта с помощью Maven
RUN mvn -f /home/application/pom.xml clean package

# Используем минимальный образ с Java для выполнения приложения
FROM openjdk:17

# Копируем JAR-файл из этапа сборки в образ
COPY --from=build /home/application/target/application.jar /usr/local/lib/application.jar

#Определяем порт, который будет использоваться
EXPOSE 8080

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "usr/local/lib/application.jar"]
