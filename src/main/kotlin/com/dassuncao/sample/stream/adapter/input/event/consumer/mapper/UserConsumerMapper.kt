package com.dassuncao.sample.stream.adapter.input.event.consumer.mapper

import com.dassuncao.UserMessage
import com.dassuncao.sample.stream.application.domain.User
import org.springframework.stereotype.Component

@Component
class UserConsumerMapper {

    fun toUser(userMessage: UserMessage): User {
        return User(
                email = userMessage.getEmail(),
                password = userMessage.getPassword()
        )
    }
}
