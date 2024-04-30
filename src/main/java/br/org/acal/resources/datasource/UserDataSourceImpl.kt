package br.org.acal.resources.datasource

import br.org.acal.domain.datasource.UserDataSource
import br.org.acal.domain.entity.User
import br.org.acal.resources.repository.UserRepository
import org.springframework.stereotype.Repository

@Repository
open class UserDataSourceImpl(private val userRepository: UserRepository) : UserDataSource {

    override fun findUserByName(username: String): User? =
        userRepository.findUserByUsername(username).let { it?.toEntity() }

}
