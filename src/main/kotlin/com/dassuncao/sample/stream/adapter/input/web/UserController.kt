package com.dassuncao.sample.stream.adapter.input.web

import com.dassuncao.sample.stream.adapter.input.web.dto.UserRequestDTO
import com.dassuncao.sample.stream.adapter.input.web.mapper.UserControllerMapper
import com.dassuncao.sample.stream.application.port.input.CreateUserUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/user")
class UserController(
        private val userControllerMapper: UserControllerMapper,
        private val createUserUseCase: CreateUserUseCase
) {

    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequestDTO): Mono<Void> {
        return createUserUseCase.create(userControllerMapper.toUser(userRequest))
    }
}
