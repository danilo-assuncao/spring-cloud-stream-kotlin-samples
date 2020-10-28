package com.dassuncao.sample.stream.adapter.input.event.consumer

import com.dassuncao.sample.stream.UserMessage
import com.dassuncao.sample.stream.adapter.input.event.consumer.mapper.UserConsumerMapper
import com.dassuncao.sample.stream.application.port.input.ShowUserUseCase
import org.springframework.context.annotation.Bean
import org.springframework.kafka.support.Acknowledgment
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHeaders
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component
class UserConsumer(
        private val userConsumerMapper: UserConsumerMapper,
        private val showUserUseCase: ShowUserUseCase
) {

    @Bean
    fun userCreatedConsumer() = Consumer<Message<UserMessage>> { message ->
        showUserUseCase
                .showUser(userConsumerMapper.toUser(message.payload))
                .doFinally { ackManual(message.headers) }
                .subscribe()
    }

    fun ackManual(headers: MessageHeaders) =
            headers[KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment::class.java]
                    ?.acknowledge()
}
