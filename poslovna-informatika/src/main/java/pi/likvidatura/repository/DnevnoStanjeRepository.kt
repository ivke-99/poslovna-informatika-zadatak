package pi.likvidatura.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pi.likvidatura.domain.DnevnoStanje

/**
 * Spring Data SQL repository for the DnevnoStanje entity.
 */
@Repository
interface DnevnoStanjeRepository : JpaRepository<DnevnoStanje, Long>