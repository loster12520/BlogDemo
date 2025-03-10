plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "1.9.25"
}

group = "com.lignting"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
//	implementation("org.apache.shiro:shiro-spring:2.0.2", classifier = "jakarta") {
//		classifier = "jakarta"
//		// 排除可能冲突的依赖
//		exclude(group = "org.apache.shiro", module = "shiro-core")
//		exclude(group = "org.apache.shiro", module = "shiro-web")
//	}
	implementation("org.apache.shiro","shiro-spring","2.0.2", classifier = "jakarta"){
		exclude(group = "org.apache.shiro", module = "shiro-core")
		exclude(group = "org.apache.shiro", module = "shiro-web")
	}
	implementation("org.apache.shiro","shiro-core","2.0.2", classifier = "jakarta")
	implementation("org.apache.shiro","shiro-web","2.0.2", classifier = "jakarta")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
