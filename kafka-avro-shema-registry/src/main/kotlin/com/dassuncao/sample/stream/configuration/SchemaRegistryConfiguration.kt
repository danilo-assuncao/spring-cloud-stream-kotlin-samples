package com.dassuncao.sample.stream.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.cloud.schema.registry.client.ConfluentSchemaRegistryClient
import org.springframework.cloud.schema.registry.client.EnableSchemaRegistryClient
import org.springframework.cloud.schema.registry.client.SchemaRegistryClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableSchemaRegistryClient
class SchemaRegistryConfiguration(private val schemaRegistryProperties: SchemaRegistryConfigurationProperties) {

    @Bean
    fun schemaRegistryClient(): SchemaRegistryClient {
        val client = ConfluentSchemaRegistryClient()
        client.setEndpoint(schemaRegistryProperties.endpoint)
        return client
    }
}

@ConstructorBinding
@ConfigurationProperties("spring.cloud.stream.schema-registry-client")
data class SchemaRegistryConfigurationProperties(
        val endpoint: String
)
