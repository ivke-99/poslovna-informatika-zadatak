package pi.likvidatura.service.impl

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pi.likvidatura.domain.Preduzece
import pi.likvidatura.repository.PreduzeceRepository
import pi.likvidatura.service.dto.PreduzeceDTO
import pi.likvidatura.service.mapper.PreduzeceMapper
import java.util.*
import java.util.stream.Collectors

/**
 * Service Implementation for managing [Preduzece].
 */
@Service
class PreduzeceService(
    private val preduzeceRepository: PreduzeceRepository,
    private val preduzeceMapper: PreduzeceMapper
) {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(PreduzeceService::class.java)
    }

    fun save(preduzeceDTO: PreduzeceDTO): PreduzeceDTO {
        log.debug("Request to save Preduzece : {}", preduzeceDTO)
        var preduzece = Preduzece(
            naziv = preduzeceDTO.naziv,
            pib = preduzeceDTO.pib,
            maticniBroj = preduzeceDTO.maticniBroj
        )
        preduzece = preduzeceRepository.save(preduzece)
        return preduzeceMapper.toDto(preduzece);
    }

    fun findAll(): List<PreduzeceDTO> {
        log.debug("Request to get all")
        return preduzeceRepository.findAll().stream().map { entity: Preduzece ->
            preduzeceMapper.toDto(
                entity
            )
        }.collect(
            Collectors.toCollection { LinkedList() }
        )
    }

    fun findOne(id: Long): Optional<PreduzeceDTO> {
        log.debug("Request to get Preduzece : {}", id)
        return preduzeceRepository.findById(id).map { entity: Preduzece ->
            preduzeceMapper.toDto(
                entity
            )
        }
    }

    fun delete(id: Long) {
        log.debug("Request to delete Preduzece : {}", id)
        preduzeceRepository.deleteById(id)
    }
}