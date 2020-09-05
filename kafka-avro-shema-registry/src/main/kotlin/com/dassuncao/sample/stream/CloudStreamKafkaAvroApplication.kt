package com.dassuncao.sample.stream

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("com.dassuncao.sample.stream.configuration")
class CloudStreamKafkaAvroApplication

fun main(args: Array<String>) {
    runApplication<CloudStreamKafkaAvroApplication>(*args)
}
