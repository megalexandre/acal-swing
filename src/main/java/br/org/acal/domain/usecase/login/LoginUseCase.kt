package br.org.acal.domain.usecase.login

import br.org.acal.domain.datasource.UserDataSource
import br.org.acal.domain.entity.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginUseCase(
    private val userDataSource: UserDataSource,
    private val passwordEncoder: PasswordEncoder
) {

    fun execute(user: User): Boolean =
        userDataSource.findUserByName(user.username)?.let {
            passwordEncoder.matches(user.password, it.password)
        } ?: false
}
