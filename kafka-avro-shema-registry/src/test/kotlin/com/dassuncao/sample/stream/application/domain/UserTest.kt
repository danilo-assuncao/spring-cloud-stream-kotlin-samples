package com.dassuncao.sample.stream.application.domain

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class UserTest {

    @Test
    fun `should validate email correctly`() {
        // Valid email
        val userWithValidEmail = User(email = "test@example.com", password = "password123")
        assertTrue(userWithValidEmail.isValidEmail())

        // Invalid email - no @
        val userWithInvalidEmail1 = User(email = "testexample.com", password = "password123")
        assertFalse(userWithInvalidEmail1.isValidEmail())

        // Invalid email - empty
        val userWithInvalidEmail2 = User(email = "", password = "password123")
        assertFalse(userWithInvalidEmail2.isValidEmail())
    }

    @Test
    fun `should validate password correctly`() {
        // Valid password - 8 characters
        val userWithValidPassword1 = User(email = "test@example.com", password = "password")
        assertTrue(userWithValidPassword1.isValidPassword())

        // Valid password - more than 8 characters
        val userWithValidPassword2 = User(email = "test@example.com", password = "password123")
        assertTrue(userWithValidPassword2.isValidPassword())

        // Invalid password - less than 8 characters
        val userWithInvalidPassword = User(email = "test@example.com", password = "pass")
        assertFalse(userWithInvalidPassword.isValidPassword())
    }

    @Test
    fun `should validate user correctly`() {
        // Valid user
        val validUser = User(email = "test@example.com", password = "password123")
        assertTrue(validUser.isValid())

        // Invalid user - invalid email
        val userWithInvalidEmail = User(email = "testexample.com", password = "password123")
        assertFalse(userWithInvalidEmail.isValid())

        // Invalid user - invalid password
        val userWithInvalidPassword = User(email = "test@example.com", password = "pass")
        assertFalse(userWithInvalidPassword.isValid())

        // Invalid user - both invalid
        val invalidUser = User(email = "testexample.com", password = "pass")
        assertFalse(invalidUser.isValid())
    }
}