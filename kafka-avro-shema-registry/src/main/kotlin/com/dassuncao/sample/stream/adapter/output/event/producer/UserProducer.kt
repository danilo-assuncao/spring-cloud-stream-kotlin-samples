package com.dassuncao.sample.stream.adapter.output.event.producer

import com.dassuncao.UserMessage
import com.dassuncao.sample.stream.adapter.output.event.producer.mapper.UserProducerMapper
import com.dassuncao.sample.stream.application.domain.User
import com.dassuncao.sample.stream.application.port.output.ProduceUserPort
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import reactor.core.publisher.EmitterProcessor
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.function.Supplier

@Component
class UserProducer(
        private val userProducerMapper: UserProducerMapper,
        private val queue: EmitterProcessor<UserMessage>
) : ProduceUserPort {

    override fun produces(user: User): Mono<Void> {
        return Mono.just(user)
                .map { queue.onNext(userProducerMapper.toUser(user)) }
                .then()
    }

    @Bean
    fun userCreatedProducer() = Supplier<Flux<UserMessage>> { queue }
}
