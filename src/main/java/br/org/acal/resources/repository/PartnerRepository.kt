package br.org.acal.resources.repository

import br.org.acal.resources.model.PartnerModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PartnerRepository : JpaRepository<PartnerModel, Long>