package com.dassuncao.sample.stream.application.port.output

import com.dassuncao.sample.stream.application.domain.User
import reactor.core.publisher.Mono

interface ProduceUserPort {

    fun produce(user: User): Mono<Void>
}