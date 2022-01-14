package pi.likvidatura.service.impl

import org.springframework.stereotype.Service
import pi.likvidatura.domain.PoslovniPartner
import pi.likvidatura.repository.PoslovniPartnerRepository
import pi.likvidatura.service.dto.PoslovniPartnerDTO
import java.util.stream.Collectors

@Service
class PoslovniPartnerService(
    private val poslovniPartnerRepository: PoslovniPartnerRepository
){
    fun getAll(): List<PoslovniPartnerDTO> {
        return poslovniPartnerRepository.findAll()
            .stream().map { partner: PoslovniPartner? ->
                PoslovniPartnerDTO.fromEntity(
                    partner
                )
            }
            .collect(Collectors.toList())
    }
}