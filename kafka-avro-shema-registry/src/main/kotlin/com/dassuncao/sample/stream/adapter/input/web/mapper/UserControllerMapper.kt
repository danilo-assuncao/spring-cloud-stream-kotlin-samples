package com.dassuncao.sample.stream.adapter.input.web.mapper

import com.dassuncao.sample.stream.adapter.input.web.dto.UserRequestDTO
import com.dassuncao.sample.stream.application.domain.User
import org.springframework.stereotype.Component

@Component
class UserControllerMapper {

    fun toUser(userRequestDTO: UserRequestDTO): User {
        return User(
                email = userRequestDTO.email,
                password = userRequestDTO.password
        )
    }
}
