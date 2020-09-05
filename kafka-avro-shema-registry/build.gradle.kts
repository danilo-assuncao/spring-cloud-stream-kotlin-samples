import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    id("com.commercehub.gradle.plugin.avro") version "0.21.0"
    id("org.springframework.boot") version "2.3.3.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
}

group = "com.dassuncao.sample.stream"
java.sourceCompatibility = JavaVersion.VERSION_11

application {
    mainClassName = "com.dassuncao.sample.stream.CloudStreamKafkaAvroApplicationKt"
}

avro {
    isCreateSetters.set(true)
    isCreateOptionalGetters.set(false)
    isGettersReturnOptional.set(false)
    isOptionalGettersForNullableFieldsOnly.set(false)
    fieldVisibility.set("PUBLIC_DEPRECATED")
    outputCharacterEncoding.set("UTF-8")
    stringType.set("String")
    templateDirectory.set(null as String?)
    isEnableDecimalLogicalType.set(true)
}

repositories {
    mavenCentral()
    maven(url = "https://packages.confluent.io/maven/")
}

val avroVersion = "1.10.0"
val springCloudVersion = "Hoxton.SR8"
val confluentLibsVersion = "5.3.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
    implementation("org.springframework.cloud:spring-cloud-stream")
    implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka")
    implementation("org.springframework.cloud:spring-cloud-schema-registry-client")
    implementation("io.confluent:kafka-avro-serializer:$confluentLibsVersion")
    implementation("org.apache.avro:avro:$avroVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.cloud:spring-cloud-stream-test-support")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events = setOf(PASSED, FAILED, SKIPPED, STANDARD_OUT, STANDARD_ERROR)
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
