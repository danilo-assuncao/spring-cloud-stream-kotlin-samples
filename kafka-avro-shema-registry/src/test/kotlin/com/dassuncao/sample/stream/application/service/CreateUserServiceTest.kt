package com.dassuncao.sample.stream.application.service

import com.dassuncao.sample.stream.application.domain.User
import com.dassuncao.sample.stream.application.port.output.ProduceUserPort
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

@ExtendWith(MockitoExtension::class)
class CreateUserServiceTest {

    @Mock
    private lateinit var produceUserPort: ProduceUserPort

    private lateinit var createUserService: CreateUserService

    @BeforeEach
    fun setUp() {
        createUserService = CreateUserService(produceUserPort)
    }

    @Test
    fun `should create user when user is valid`() {
        // Given
        val validUser = User(email = "test@example.com", password = "password123")
        `when`(produceUserPort.produce(validUser)).thenReturn(Mono.empty())

        // When
        val result = createUserService.create(validUser)

        // Then
        StepVerifier.create(result)
            .verifyComplete()

        verify(produceUserPort).produce(validUser)
    }

    @Test
    fun `should return error when user has invalid email`() {
        // Given
        val userWithInvalidEmail = User(email = "testexample.com", password = "password123")

        // When
        val result = createUserService.create(userWithInvalidEmail)

        // Then
        StepVerifier.create(result)
            .expectError(IllegalArgumentException::class.java)
            .verify()

        verifyNoInteractions(produceUserPort)
    }

    @Test
    fun `should return error when user has invalid password`() {
        // Given
        val userWithInvalidPassword = User(email = "test@example.com", password = "pass")

        // When
        val result = createUserService.create(userWithInvalidPassword)

        // Then
        StepVerifier.create(result)
            .expectError(IllegalArgumentException::class.java)
            .verify()

        verifyNoInteractions(produceUserPort)
    }
}