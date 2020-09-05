package com.dassuncao.sample.stream.configuration

import com.dassuncao.sample.stream.UserMessage
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.EmitterProcessor

@Configuration
class EmitterProcessorConfiguration {

    @Bean
    fun userWithoutEmitterProcessor() =
            EmitterProcessor.create<UserMessage>()
}
