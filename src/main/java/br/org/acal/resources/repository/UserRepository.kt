package br.org.acal.resources.repository

import br.org.acal.resources.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserModel, Long> {

    fun findUserByUsername(username: String): UserModel?

}