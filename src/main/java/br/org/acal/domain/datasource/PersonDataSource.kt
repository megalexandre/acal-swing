package br.org.acal.domain.datasource

import br.org.acal.domain.entity.Person
import org.springframework.stereotype.Repository

@Repository
fun interface PersonDataSource {
    fun findUserByNameLike(username: String): Collection<Person>
}
