package pi.likvidatura.service.impl

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pi.likvidatura.domain.PoslovnaGodina
import pi.likvidatura.repository.PoslovnaGodinaRepository
import pi.likvidatura.service.dto.PoslovnaGodinaDTO
import pi.likvidatura.service.mapper.PoslovnaGodinaMapper
import java.util.*
import java.util.stream.Collectors

/**
 * Service Implementation for managing [PoslovnaGodina].
 */
@Service
class PoslovnaGodinaService(
    private val poslovnaGodinaRepository: PoslovnaGodinaRepository,
    private val poslovnaGodinaMapper: PoslovnaGodinaMapper
)
{
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(PoslovnaGodinaService::class.java)
    }

    fun save(poslovnaGodinaDTO: PoslovnaGodinaDTO): PoslovnaGodinaDTO {
        log.debug("Request to save PoslovnaGodina : {}", poslovnaGodinaDTO)
        var poslovnaGodina = poslovnaGodinaMapper.toEntity(poslovnaGodinaDTO)
        poslovnaGodina = poslovnaGodinaRepository.save(poslovnaGodina)
        return poslovnaGodinaMapper.toDto(poslovnaGodina)
    }

    fun findAll(): List<PoslovnaGodinaDTO> {
        log.debug("Request to get all")
        return poslovnaGodinaRepository
            .findAll()
            .stream()
            .map { s: PoslovnaGodina? ->
                poslovnaGodinaMapper.toDto(
                    s
                )
            }
            .collect(
                Collectors.toCollection { LinkedList() }
            )
    }

    fun findOne(id: Long): Optional<PoslovnaGodinaDTO> {
        log.debug("Request to get PoslovnaGodina : {}", id)
        return poslovnaGodinaRepository.findById(id).map { s: PoslovnaGodina? ->
            poslovnaGodinaMapper.toDto(
                s
            )
        }
    }

    fun delete(id: Long) {
        log.debug("Request to delete PoslovnaGodina : {}", id)
        poslovnaGodinaRepository.deleteById(id)
    }
}