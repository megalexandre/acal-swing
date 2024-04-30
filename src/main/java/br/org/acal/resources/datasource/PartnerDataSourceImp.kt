package br.org.acal.resources.datasource

import br.org.acal.domain.datasource.PartnerDataSource
import br.org.acal.resources.repository.PartnerRepository
import org.springframework.stereotype.Repository

@Repository
open class PartnerDataSourceImp(private val repository: PartnerRepository) : PartnerDataSource
