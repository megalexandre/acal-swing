package br.org.acal.resources.datasource

import br.org.acal.domain.datasource.PersonDataSource
import br.org.acal.domain.entity.Person
import br.org.acal.resources.repository.PersonRepository
import org.springframework.stereotype.Repository

@Repository
open class PersonDataSourceImp(private val repository: PersonRepository) : PersonDataSource {

    override fun findUserByNameLike(username: String): Collection<Person> =
        repository
            .findUserByNameLike("%$username%")
            .map { it.toEntity() }
}
