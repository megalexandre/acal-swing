package br.org.acal.resources.repository

import br.org.acal.resources.model.PersonModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<PersonModel, Long> {
    fun findUserByNameLike(@Param("name") name: String): Collection<PersonModel>
}