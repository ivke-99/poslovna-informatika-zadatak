package pi.likvidatura.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pi.likvidatura.domain.Banka

/**
 * Spring Data SQL repository for the Banka entity.
 */
@Repository
interface BankaRepository : JpaRepository<Banka, Long>