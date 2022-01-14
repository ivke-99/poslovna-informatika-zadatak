package pi.likvidatura.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pi.likvidatura.domain.Preduzece

/**
 * Spring Data SQL repository for the Preduzece entity.
 */
@Repository
interface PreduzeceRepository : JpaRepository<Preduzece, Long>