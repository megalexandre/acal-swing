package br.org.acal.domain.usecase.person

import br.org.acal.domain.datasource.PersonDataSource
import br.org.acal.domain.entity.Person
import br.org.acal.domain.usecase.Usecase
import org.springframework.stereotype.Service

@Service
class PersonFindByNameUsecase(
    private val personDataSource: PersonDataSource
) : Usecase<String, Collection<Person>> {

    override fun execute(input: String): Collection<Person> =
        personDataSource.findUserByNameLike(input)

}