package pi.likvidatura.repository

import org.springframework.data.jpa.repository.JpaRepository
import pi.likvidatura.domain.PoslovniPartner

interface PoslovniPartnerRepository : JpaRepository<PoslovniPartner, Long>