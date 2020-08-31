package com.dassuncao.sample.stream.adapter.output.event.producer.mapper

import com.dassuncao.UserMessage
import com.dassuncao.sample.stream.application.domain.User
import org.springframework.stereotype.Component

@Component
class UserProducerMapper {

    fun toUser(user: User): UserMessage {
        return UserMessage
                .newBuilder()
                .setEmail(user.email)
                .setPassword(user.password)
                .build()
    }
}
