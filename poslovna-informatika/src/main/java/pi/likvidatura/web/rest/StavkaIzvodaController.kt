package pi.likvidatura.web.rest

import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pi.likvidatura.repository.StavkaIzvodaRepository
import pi.likvidatura.service.dto.StavkaIzvodaDTO
import pi.likvidatura.service.impl.StavkaIzvodaService
import java.net.URI
import java.net.URISyntaxException
import java.util.*

/**
 * REST controller for managing [pi.likvidatura.domain.StavkaIzvoda].
 */
@CrossOrigin
@RestController
@RequestMapping("/api/stavke-izvoda")
class StavkaIzvodaController(private val stavkaIzvodaService: StavkaIzvodaService,
                             private val stavkaIzvodaRepository: StavkaIzvodaRepository) {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(StavkaIzvodaController::class.java)
    }

    @PostMapping
    @Throws(URISyntaxException::class)
    fun createStavkaIzvoda(@RequestBody stavkaIzvodaDTO: StavkaIzvodaDTO): ResponseEntity<StavkaIzvodaDTO> {
        log.debug("REST request to save StavkaIzvoda : {}", stavkaIzvodaDTO)
        if (stavkaIzvodaDTO.id != null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        val result: StavkaIzvodaDTO = stavkaIzvodaService.save(stavkaIzvodaDTO)
        return ResponseEntity
            .created(URI("/api/stavke-izvoda/" + result.id))
            .body(result)
    }

    @PutMapping("/{id}")
    @Throws(URISyntaxException::class)
    fun updateStavkaIzvoda(
        @PathVariable(value = "id", required = false) id: Long,
        @RequestBody stavkaIzvodaDTO: StavkaIzvodaDTO
    ): ResponseEntity<StavkaIzvodaDTO> {
        log.debug("REST request to update StavkaIzvoda : {}, {}", id, stavkaIzvodaDTO)
        if (stavkaIzvodaDTO.id == null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        if (id != stavkaIzvodaDTO.id) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        if (!stavkaIzvodaRepository.existsById(id)) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        val result: StavkaIzvodaDTO = stavkaIzvodaService.save(stavkaIzvodaDTO)
        return ResponseEntity
            .ok()
            .body(result)
    }

    @GetMapping
    fun getAllStavkeIzvoda(
        @RequestParam(defaultValue = "") svrhaPlacanja: String?,
        @RequestParam(defaultValue = "0") pageNum: Int
    ): Page<StavkaIzvodaDTO> {
        log.debug("REST request to get all")
        return stavkaIzvodaService.findAll(svrhaPlacanja ?: "", pageNum)
    }

    @GetMapping("/{id}")
    fun getStavkaIzvoda(@PathVariable id: Long): ResponseEntity<StavkaIzvodaDTO> {
        log.debug("REST request to get StavkaIzvoda : {}", id)
        val stavkaIzvodaDTO: Optional<StavkaIzvodaDTO> = stavkaIzvodaService.findOne(id)
        return if (stavkaIzvodaDTO.isEmpty) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else ResponseEntity
            .ok()
            .body(stavkaIzvodaDTO.get())
    }

    @DeleteMapping("/{id}")
    fun deleteStavkaIzvoda(@PathVariable id: Long): ResponseEntity<Void> {
        log.debug("REST request to delete StavkaIzvoda : {}", id)
        stavkaIzvodaService.delete(id)
        return ResponseEntity
            .noContent()
            .build()
    }
}