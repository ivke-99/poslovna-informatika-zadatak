package pi.likvidatura.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pi.likvidatura.domain.BankarskiRacun

/**
 * Spring Data SQL repository for the BankarskiRacun entity.
 */
@Repository
interface BankarskiRacunRepository : JpaRepository<BankarskiRacun, Long>