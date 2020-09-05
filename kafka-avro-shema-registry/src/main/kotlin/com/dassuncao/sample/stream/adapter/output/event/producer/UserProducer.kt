package com.dassuncao.sample.stream.adapter.output.event.producer

import com.dassuncao.sample.stream.UserMessage
import com.dassuncao.sample.stream.adapter.output.event.producer.mapper.UserProducerMapper
import com.dassuncao.sample.stream.application.domain.User
import com.dassuncao.sample.stream.application.port.output.ProduceUserPort
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import reactor.core.publisher.EmitterProcessor
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.SignalType
import java.util.function.Supplier
import java.util.logging.Level

@Component
class UserProducer(
        private val userProducerMapper: UserProducerMapper,
        private val queue: EmitterProcessor<UserMessage>
) : ProduceUserPort {

    override fun produce(user: User): Mono<Void> {
        return Mono.just(user)
                .log("${this.javaClass.name} - Produced", Level.INFO, SignalType.ON_NEXT)
                .map { queue.onNext(userProducerMapper.toUser(user)) }
                .then()
    }

    @Bean
    fun userCreatedProducer() = Supplier<Flux<UserMessage>> { queue }
}
