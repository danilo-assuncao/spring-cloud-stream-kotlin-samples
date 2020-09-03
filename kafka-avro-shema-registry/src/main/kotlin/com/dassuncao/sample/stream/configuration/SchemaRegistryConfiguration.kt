package com.dassuncao.sample.stream.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.schema.registry.client.ConfluentSchemaRegistryClient
import org.springframework.cloud.schema.registry.client.EnableSchemaRegistryClient
import org.springframework.cloud.schema.registry.client.SchemaRegistryClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableSchemaRegistryClient
class SchemaRegistryConfiguration {

    @Bean
    fun schemaRegistryClient(@Value("\${spring.cloud.stream.schemaRegistryClient.endpoint}") endpoint: String): SchemaRegistryClient {
        val client = ConfluentSchemaRegistryClient()
        client.setEndpoint("http://localhost:8081")
        return client
    }
}
