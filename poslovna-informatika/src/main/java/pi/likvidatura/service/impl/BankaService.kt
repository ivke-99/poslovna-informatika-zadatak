package pi.likvidatura.service.impl

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pi.likvidatura.domain.Banka
import pi.likvidatura.repository.BankaRepository
import pi.likvidatura.service.dto.BankaDTO
import pi.likvidatura.service.mapper.BankaMapper
import java.util.*
import java.util.stream.Collectors

/**
 * Service Implementation for managing [Banka].
 */
@Service
class BankaService(
    private val bankaRepository: BankaRepository,
    private val bankaMapper: BankaMapper) {

    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(BankaService::class.java)
    }

    fun save(bankaDTO: BankaDTO): BankaDTO {
        log.debug("Request to save Banka : {}", bankaDTO)
        var banka = bankaMapper.toEntity(bankaDTO)
        banka = bankaRepository.save(banka)
        return bankaMapper.toDto(banka)
    }


    fun findAll(): List<BankaDTO> {
        log.debug("Request to get all")
        return bankaRepository.findAll().stream().map { entity: Banka ->
            bankaMapper.toDto(
                entity
            )
        }.collect(
            Collectors.toCollection { LinkedList() }
        )
    }

    fun findOne(id: Long): Optional<BankaDTO> {
        log.debug("Request to get Banka : {}", id)
        return bankaRepository.findById(id).map { entity: Banka ->
            bankaMapper.toDto(
                entity
            )
        }
    }

    fun delete(id: Long) {
        log.debug("Request to delete Banka : {}", id)
        bankaRepository.deleteById(id)
    }
}