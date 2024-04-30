package br.org.acal.resources.model

import br.org.acal.domain.entity.Person
import jakarta.persistence.Basic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "person")
data class PersonModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private var id: Int,

    @Basic(optional = false)
    @Column(name = "name")
    private var name: String,

    @Column(name = "address_number")
    private var addressNumber: String,

){

    fun toEntity(): Person =
        Person(id = id.toString(), name = name, addressNumber = addressNumber)

}

fun Person.toModel(): PersonModel = PersonModel(
    id =  id.toInt(),
    name = name,
    addressNumber = addressNumber,
)