FROM maven:latest AS build

# set working directory
WORKDIR /app

# Copy project file
COPY pom.xml ./

# install dependencies
RUN mvn dependency:go-offline

# copy all project
COPY . .

# Disable mongodb tests
ENV MONGO_ENABLED=false

# compile source code
RUN mvn package

# Runtime stage
FROM java:latest

# set working directory
WORKDIR /app

# Copy compiled binaries
COPY --from=build /app/lib ./lib

# Copy configuration
COPY config/*.* ./config/

ENV MONGO_SERVICE_URI ""
ENV MONGO_SERVICE_HOST mongo
ENV MONGO_SERVICE_PORT 27017
ENV MONGO_DB app

EXPOSE 8080

CMD ["java", "-jar", "./lib/run.jar", "-c", "./config/config.yml"]
