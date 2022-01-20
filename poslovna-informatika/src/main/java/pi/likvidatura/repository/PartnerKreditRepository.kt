package pi.likvidatura.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pi.likvidatura.domain.PartnerKredit
import javax.servlet.http.Part

@Repository
interface PartnerKreditRepository: JpaRepository<PartnerKredit, Long> {
    fun findByPoslovniPartnerId(id: Long) : PartnerKredit?
}