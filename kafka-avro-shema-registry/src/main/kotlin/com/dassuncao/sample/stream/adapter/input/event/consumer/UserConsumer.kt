package com.dassuncao.sample.stream.adapter.input.event.consumer

import com.dassuncao.sample.stream.UserMessage
import com.dassuncao.sample.stream.adapter.input.event.consumer.mapper.UserConsumerMapper
import com.dassuncao.sample.stream.application.port.input.ShowUserUseCase
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component
class UserConsumer(
        private val userConsumerMapper: UserConsumerMapper,
        private val showUserUseCase: ShowUserUseCase
) {

    @Bean
    fun userCreatedConsumer() = Consumer<UserMessage> {
        showUserUseCase
                .showUser(userConsumerMapper.toUser(it))
                .subscribe()
    }
}
