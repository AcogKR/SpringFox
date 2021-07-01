plugins {
    `publishing-module`
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("org.springframework.boot", "spring-boot-starter", "2.4.5")
    implementation("io.github.microutils", "kotlin-logging-jvm", "2.0.8")
}