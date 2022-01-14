package pi.likvidatura.web.rest

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pi.likvidatura.repository.PreduzeceRepository
import pi.likvidatura.service.dto.PreduzeceDTO
import pi.likvidatura.service.impl.PreduzeceService
import java.net.URI
import java.net.URISyntaxException
import java.util.*

/**
 * REST controller for managing [pi.likvidatura.domain.Preduzece].
 */
@CrossOrigin
@RestController
@RequestMapping("/api/preduzeca")
class PreduzeceController(
        private val preduzeceService: PreduzeceService,
        private val preduzeceRepository: PreduzeceRepository) {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(PreduzeceController::class.java)
    }

    @PostMapping
    @Throws(URISyntaxException::class)
    fun createPreduzece(@RequestBody preduzeceDTO: PreduzeceDTO): ResponseEntity<PreduzeceDTO> {
        log.debug("REST request to save Preduzece : {}", preduzeceDTO)
        if (preduzeceDTO.id != null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        val result: PreduzeceDTO = preduzeceService.save(preduzeceDTO)
        return ResponseEntity
            .created(URI("/api/preduzeca/" + result.id))
            .body(result)
    }

    @PutMapping("/{id}")
    @Throws(URISyntaxException::class)
    fun updatePreduzece(
        @PathVariable(value = "id", required = false) id: Long,
        @RequestBody preduzeceDTO: PreduzeceDTO
    ): ResponseEntity<PreduzeceDTO> {
        log.debug("REST request to update Preduzece : {}, {}", id, preduzeceDTO)
        if (preduzeceDTO.id == null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        if (id != preduzeceDTO.id) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        if (!preduzeceRepository.existsById(id)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        val result: PreduzeceDTO = preduzeceService.save(preduzeceDTO)
        return ResponseEntity
            .ok()
            .body(result)
    }

    @GetMapping
    fun allPreduzeca(): List<PreduzeceDTO>
        {
            log.debug("REST request to get all")
            return preduzeceService.findAll()
        }

    @GetMapping("/{id}")
    fun getPreduzece(@PathVariable id: Long): ResponseEntity<PreduzeceDTO> {
        val preduzeceDTO: Optional<PreduzeceDTO> = preduzeceService.findOne(id)
        return if (preduzeceDTO.isEmpty) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else ResponseEntity
            .ok()
            .body(preduzeceDTO.get())
    }

    @DeleteMapping("/{id}")
    fun deletePreduzece(@PathVariable id: Long): ResponseEntity<Void> {
        log.debug("REST request to delete Preduzece : {}", id)
        preduzeceService.delete(id)
        return ResponseEntity
            .noContent()
            .build()
    }
}