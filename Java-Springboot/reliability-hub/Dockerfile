# ── Stage 1: build ──────────────────────────────────────────────────────────────
FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

# Copy Gradle wrapper and dependency descriptors first for layer caching
COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle.kts settings.gradle.kts ./

# Pre-fetch dependencies (cached unless build files change)
RUN ./gradlew dependencies --no-daemon -q 2>/dev/null || true

# Copy source and build the fat JAR (skip tests in the image build)
COPY src/ src/
RUN ./gradlew bootJar --no-daemon -x test

# ── Stage 2: run ────────────────────────────────────────────────────────────────
FROM eclipse-temurin:17-jre-jammy AS runtime

# Non-root user for security
RUN useradd --system --no-create-home appuser
USER appuser

WORKDIR /app

# Copy only the fat JAR from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
