package com.dassuncao.sample.stream.application.port.input

import com.dassuncao.sample.stream.application.domain.User
import reactor.core.publisher.Mono

interface ShowUserUseCase {

    fun showUser(user: User): Mono<Void>
}