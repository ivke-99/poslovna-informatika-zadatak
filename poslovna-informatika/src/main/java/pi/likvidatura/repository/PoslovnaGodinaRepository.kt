package pi.likvidatura.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pi.likvidatura.domain.PoslovnaGodina

/**
 * Spring Data SQL repository for the PoslovnaGodina entity.
 */
@Repository
interface PoslovnaGodinaRepository : JpaRepository<PoslovnaGodina, Long>