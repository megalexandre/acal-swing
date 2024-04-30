package br.org.acal.resources.model

import br.org.acal.domain.entity.Partner
import jakarta.persistence.Basic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.io.Serializable
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Entity
@Table(name = "socio")
data class PartnerModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private val id: String,

    @Column(name = "carneDiferenciado")
    private val specialInvoice: Boolean,

    @Column(name = "SocioExclusivo")
    private val patternOnly: Boolean,

    @Basic(optional = false)
    @Column(name = "numeroSocio")
    private val number: String,

    @MapsId
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPessoa", referencedColumnName = "id")
    private val personModel: PersonModel,

)  {

    fun toEntity(): Partner =
        Partner(
            id = id,
            specialInvoice = specialInvoice,
            patternOnly = patternOnly,
            person = personModel.toEntity(),
            number =  number
        )
}

fun Partner.toModel(): PartnerModel = PartnerModel(
    id = id,
    specialInvoice = specialInvoice,
    patternOnly = patternOnly,
    number = number,
    personModel = person.toModel(),
)