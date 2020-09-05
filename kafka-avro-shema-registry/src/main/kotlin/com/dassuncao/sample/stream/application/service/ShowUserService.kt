package com.dassuncao.sample.stream.application.service

import com.dassuncao.sample.stream.application.domain.User
import com.dassuncao.sample.stream.application.port.input.ShowUserUseCase
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.SignalType.ON_NEXT
import java.util.logging.Level.INFO

@Service
class ShowUserService : ShowUserUseCase {

    override fun showUser(user: User): Mono<Void> {
        return Mono.just(user)
                .log("${this.javaClass.name} - Consumed", INFO, ON_NEXT)
                .then()
    }
}
