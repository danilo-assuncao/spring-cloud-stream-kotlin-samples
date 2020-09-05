package com.dassuncao.sample.stream.application.service

import com.dassuncao.sample.stream.application.domain.User
import com.dassuncao.sample.stream.application.port.input.CreateUserUseCase
import com.dassuncao.sample.stream.application.port.output.ProduceUserPort
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CreateUserService(private val produceUserPort: ProduceUserPort) : CreateUserUseCase {

    override fun create(user: User): Mono<Void> {
        return produceUserPort.produce(user)
    }
}
