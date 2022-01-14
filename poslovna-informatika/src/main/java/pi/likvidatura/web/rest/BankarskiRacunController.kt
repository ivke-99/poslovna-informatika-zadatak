package pi.likvidatura.web.rest

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pi.likvidatura.repository.BankarskiRacunRepository
import pi.likvidatura.service.dto.BankarskiRacunDTO
import pi.likvidatura.service.impl.BankarskiRacunService
import java.net.URI
import java.net.URISyntaxException
import java.util.*

/**
 * REST controller for managing [pi.likvidatura.domain.BankarskiRacun].
 */
@CrossOrigin
@RestController
@RequestMapping("/api/bankarski-racuni")
class BankarskiRacunController(
    private val bankarskiRacunService: BankarskiRacunService,
    private val bankarskiRacunRepository: BankarskiRacunRepository
) {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(BankarskiRacunController::class.java)
    }

    @PostMapping
    @Throws(URISyntaxException::class)
    fun createBankarskiRacun(@RequestBody bankarskiRacunDTO: BankarskiRacunDTO): ResponseEntity<BankarskiRacunDTO> {
        log.debug("REST request to save BankarskiRacun : {}", bankarskiRacunDTO)
        if (bankarskiRacunDTO.id != null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        val result: BankarskiRacunDTO = bankarskiRacunService.save(bankarskiRacunDTO)
        return ResponseEntity
            .created(URI("/api/bankarski-racuni/" + result.id))
            .body(result)
    }

    @PutMapping("/{id}")
    @Throws(URISyntaxException::class)
    fun updateBankarskiRacun(
        @PathVariable(value = "id", required = false) id: Long,
        @RequestBody bankarskiRacunDTO: BankarskiRacunDTO
    ): ResponseEntity<BankarskiRacunDTO> {
        log.debug("REST request to update BankarskiRacun : {}, {}", id, bankarskiRacunDTO)
        if (bankarskiRacunDTO.id == null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        if (id != bankarskiRacunDTO.id) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        if (!bankarskiRacunRepository.existsById(id)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        val result: BankarskiRacunDTO = bankarskiRacunService.save(bankarskiRacunDTO)
        return ResponseEntity
            .ok()
            .body(result)
    }

    @GetMapping
    fun allBankarskiRacuni(): List<BankarskiRacunDTO> {
            return bankarskiRacunService.findAll()
        }

    @GetMapping("/{id}")
    fun getBankarskiRacun(@PathVariable id: Long): ResponseEntity<BankarskiRacunDTO> {
        log.debug("REST request to get BankarskiRacun : {}", id)
        val bankarskiRacunDTO = bankarskiRacunService.findOne(id)
        return if (bankarskiRacunDTO.isEmpty) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else ResponseEntity
            .ok()
            .body(bankarskiRacunDTO.get())
    }

    @DeleteMapping("/{id}")
    fun deleteBankarskiRacun(@PathVariable id: Long): ResponseEntity<Void> {
        log.debug("REST request to delete BankarskiRacun : {}", id)
        bankarskiRacunService.delete(id)
        return ResponseEntity
            .noContent()
            .build()
    }
}