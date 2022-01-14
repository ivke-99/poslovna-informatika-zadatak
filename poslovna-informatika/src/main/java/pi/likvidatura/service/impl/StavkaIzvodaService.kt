package pi.likvidatura.service.impl

import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pi.likvidatura.domain.StavkaIzvoda
import pi.likvidatura.repository.StavkaIzvodaRepository
import pi.likvidatura.service.dto.StavkaIzvodaDTO
import pi.likvidatura.service.mapper.StavkaIzvodaMapper
import java.util.*

/**
 * Service Implementation for managing [StavkaIzvoda].
 */
@Service
class StavkaIzvodaService(
    private val stavkaIzvodaRepository: StavkaIzvodaRepository,
    private val stavkaIzvodaMapper: StavkaIzvodaMapper
) {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(StavkaIzvodaService::class.java)
    }
    fun save(stavkaIzvodaDTO: StavkaIzvodaDTO): StavkaIzvodaDTO {
        log.debug("Request to save StavkaIzvoda : {}", stavkaIzvodaDTO)
        var stavkaIzvoda = stavkaIzvodaMapper.toEntity(stavkaIzvodaDTO)
        stavkaIzvoda = stavkaIzvodaRepository.save(stavkaIzvoda)
        return stavkaIzvodaMapper.toDto(stavkaIzvoda)
    }

    fun findAll(
        svrhaPlacanja: String,
        pageNum: Int
    ): Page<StavkaIzvodaDTO> {
        log.debug("Request to get all")
        return stavkaIzvodaRepository
            .findAllBySvrhaPlacanjaContaining(svrhaPlacanja, PageRequest.of(pageNum, 10))
            .map { stavka: StavkaIzvoda? -> StavkaIzvodaDTO.fromEntity(stavka) }
    }

    fun findOne(id: Long): Optional<StavkaIzvodaDTO> {
        log.debug("Request to get StavkaIzvoda : {}", id)
        return stavkaIzvodaRepository.findById(id).map { s: StavkaIzvoda? ->
            stavkaIzvodaMapper.toDto(
                s
            )
        }
    }

    fun delete(id: Long) {
        log.debug("Request to delete StavkaIzvoda : {}", id)
        stavkaIzvodaRepository.deleteById(id)
    }
}