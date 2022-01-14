package pi.likvidatura.service.impl

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pi.likvidatura.domain.BankarskiRacun
import pi.likvidatura.repository.BankarskiRacunRepository
import pi.likvidatura.service.dto.BankarskiRacunDTO
import pi.likvidatura.service.mapper.BankarskiRacunMapper
import java.util.*
import java.util.stream.Collectors

/**
 * Service Implementation for managing [BankarskiRacun].
 */
@Service
class BankarskiRacunService(
    private val bankarskiRacunRepository: BankarskiRacunRepository,
    private val bankarskiRacunMapper: BankarskiRacunMapper
) {
    fun save(bankarskiRacunDTO: BankarskiRacunDTO): BankarskiRacunDTO {
        var bankarskiRacun = bankarskiRacunMapper.toEntity(bankarskiRacunDTO)
        bankarskiRacun = bankarskiRacunRepository.save(bankarskiRacun)
        return bankarskiRacunMapper.toDto(bankarskiRacun)
    }

    fun findAll(): List<BankarskiRacunDTO> {
        return bankarskiRacunRepository
            .findAll()
            .stream()
            .map { s: BankarskiRacun? ->
                bankarskiRacunMapper.toDto(
                    s
                )
            }
            .collect(
                Collectors.toCollection { LinkedList() }
            )
    }

    fun findOne(id: Long): Optional<BankarskiRacunDTO> {
        return bankarskiRacunRepository.findById(id).map { s: BankarskiRacun? ->
            bankarskiRacunMapper.toDto(
                s
            )
        }
    }

    fun delete(id: Long) {
        bankarskiRacunRepository.deleteById(id)
    }
}