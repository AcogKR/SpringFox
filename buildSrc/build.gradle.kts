plugins {
    `kotlin-dsl`
}

repositories {
    maven("https://repo.heartpattern.io/repository/maven-public/")
}

dependencies {
    implementation("org.jetbrains.kotlin", "kotlin-gradle-plugin", "1.5.20")
    implementation("org.jetbrains.kotlin", "kotlin-allopen", "1.5.20")
    implementation("org.jetbrains.kotlin", "kotlin-serialization", "1.5.20")
    implementation("org.jetbrains.kotlin", "kotlin-gradle-plugin", "1.5.20")
    implementation("org.jetbrains.dokka", "dokka-gradle-plugin", "1.4.32")
    implementation("org.springframework.boot", "spring-boot-gradle-plugin", "2.5.2")
    implementation("io.spring.gradle", "dependency-management-plugin", "1.0.11.RELEASE")
}