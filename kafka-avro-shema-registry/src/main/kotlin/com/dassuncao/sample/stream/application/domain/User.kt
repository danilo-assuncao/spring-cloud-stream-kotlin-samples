package com.dassuncao.sample.stream.application.domain

data class User(
        val email: String,
        val password: String
) {
    companion object {
        private const val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$"
        private const val MIN_PASSWORD_LENGTH = 8
    }

    fun isValidEmail(): Boolean {
        return email.matches(EMAIL_REGEX.toRegex())
    }

    fun isValidPassword(): Boolean {
        return password.length >= MIN_PASSWORD_LENGTH
    }

    fun isValid(): Boolean {
        return isValidEmail() && isValidPassword()
    }
}
