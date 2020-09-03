package com.dassuncao.sample.stream.application.port.input

import com.dassuncao.sample.stream.application.domain.User
import reactor.core.publisher.Mono

interface CreateUserUseCase {

    fun create(user: User): Mono<Void>
}
