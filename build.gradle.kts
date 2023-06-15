plugins {
	java
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "dev.decision"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator:3.1.0")
	implementation("org.springframework.boot:spring-boot-starter-web:3.1.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.1.0")
	implementation("com.h2database:h2:2.1.214")
	implementation("org.drools:drools-core:8.39.0.Final")
	implementation("org.drools:drools-decisiontables:8.39.0.Final")
	implementation("org.drools:drools-mvel:8.39.0.Final")
	developmentOnly("org.springframework.boot:spring-boot-devtools:3.1.0")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.1.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.0")
	testImplementation("io.rest-assured:rest-assured:5.3.0")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
