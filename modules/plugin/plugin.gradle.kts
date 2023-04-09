plugins {
    `publishing-module`
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")
    implementation("io.heartpattern.springfox:springfox-starter:0.1.15")
}