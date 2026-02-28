plugins {
	java
	id("org.springframework.boot") version "4.0.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "dev.shafin"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
    // REST API
    implementation("org.springframework.boot:spring-boot-starter-web")

    // SRE/Production endpoints: /actuator/health, metrics, readiness/liveness, etc.
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Prometheus scrape endpoint: /actuator/prometheus (optional but recommended)
    implementation("io.micrometer:micrometer-registry-prometheus")

    // Quality-of-life for local dev only
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // JUnit launcher (optional; you can keep it)
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
