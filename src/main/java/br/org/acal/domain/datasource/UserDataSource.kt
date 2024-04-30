package br.org.acal.domain.datasource

import br.org.acal.domain.entity.User
import org.springframework.stereotype.Repository

@Repository
fun interface UserDataSource {
    fun findUserByName(username: String): User?
}
