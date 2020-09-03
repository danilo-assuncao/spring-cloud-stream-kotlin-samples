package com.dassuncao.sample.stream

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CloudStreamKafkaAvroApplication

fun main(args: Array<String>) {
    runApplication<CloudStreamKafkaAvroApplication>(*args)
}
