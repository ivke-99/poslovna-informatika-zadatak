package pi.likvidatura.web.rest

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pi.likvidatura.repository.PoslovnaGodinaRepository
import pi.likvidatura.service.dto.PoslovnaGodinaDTO
import pi.likvidatura.service.impl.PoslovnaGodinaService
import java.net.URI
import java.net.URISyntaxException
import java.util.*

/**
 * REST controller for managing [pi.likvidatura.domain.PoslovnaGodina].
 */
@CrossOrigin
@RestController
@RequestMapping("/api/poslovne-godine")
class PoslovnaGodinaController(
    private val poslovnaGodinaService: PoslovnaGodinaService,
    private val poslovnaGodinaRepository: PoslovnaGodinaRepository
) {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(PoslovnaGodinaController::class.java)
    }

    @PostMapping
    @Throws(URISyntaxException::class)
    fun createPoslovnaGodina(@RequestBody poslovnaGodinaDTO: PoslovnaGodinaDTO): ResponseEntity<PoslovnaGodinaDTO> {
        log.debug("REST request to save PoslovnaGodina : {}", poslovnaGodinaDTO)
        if (poslovnaGodinaDTO.id != null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        val result: PoslovnaGodinaDTO = poslovnaGodinaService.save(poslovnaGodinaDTO)
        return ResponseEntity
            .created(URI("/api/poslovna-godinas/" + result.id))
            .body(result)
    }

    @PutMapping("/{id}")
    @Throws(URISyntaxException::class)
    fun updatePoslovnaGodina(
        @PathVariable(value = "id", required = false) id: Long,
        @RequestBody poslovnaGodinaDTO: PoslovnaGodinaDTO
    ): ResponseEntity<PoslovnaGodinaDTO> {
        log.debug("REST request to update PoslovnaGodina : {}, {}", id, poslovnaGodinaDTO)
        if (poslovnaGodinaDTO.id == null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        if (id != poslovnaGodinaDTO.id) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        if (!poslovnaGodinaRepository.existsById(id)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        val result: PoslovnaGodinaDTO = poslovnaGodinaService.save(poslovnaGodinaDTO)
        return ResponseEntity
            .ok()
            .body(result)
    }

    @GetMapping
    fun allPoslovneGodine(): List<PoslovnaGodinaDTO> = poslovnaGodinaService.findAll()


    @GetMapping("/{id}")
    fun getPoslovnaGodina(@PathVariable id: Long): ResponseEntity<PoslovnaGodinaDTO> {
        log.debug("REST request to get PoslovnaGodina : {}", id)
        val poslovnaGodinaDTO: Optional<PoslovnaGodinaDTO> = poslovnaGodinaService.findOne(id)
        return if (poslovnaGodinaDTO.isEmpty) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else ResponseEntity
            .ok()
            .body(poslovnaGodinaDTO.get())
    }

    @DeleteMapping("/{id}")
    fun deletePoslovnaGodina(@PathVariable id: Long): ResponseEntity<Void> {
        log.debug("REST request to delete PoslovnaGodina : {}", id)
        poslovnaGodinaService.delete(id)
        return ResponseEntity
            .noContent()
            .build()
    }
}