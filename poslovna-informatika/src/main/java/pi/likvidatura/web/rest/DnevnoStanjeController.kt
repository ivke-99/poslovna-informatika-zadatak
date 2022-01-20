package pi.likvidatura.web.rest

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.badRequest
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import pi.likvidatura.repository.DnevnoStanjeRepository
import pi.likvidatura.service.dto.DnevnoStanjeDTO
import pi.likvidatura.service.impl.DnevnoStanjeService
import java.io.IOException
import java.net.URI
import java.net.URISyntaxException
import java.util.*

/**
 * REST controller for managing [pi.likvidatura.domain.DnevnoStanje].
 */
@CrossOrigin
@RestController
@RequestMapping("/api/dnevna-stanja")
class DnevnoStanjeController(private val dnevnoStanjeService: DnevnoStanjeService,
                             private val dnevnoStanjeRepository: DnevnoStanjeRepository) {

    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(DnevnoStanjeController::class.java)
    }

    @PostMapping
    @Throws(URISyntaxException::class)
    fun createDnevnoStanje(@RequestBody dnevnoStanjeDTO: DnevnoStanjeDTO): ResponseEntity<DnevnoStanjeDTO> {
        log.debug("REST request to save DnevnoStanje : {}", dnevnoStanjeDTO)
        if (dnevnoStanjeDTO.id != null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        val result: DnevnoStanjeDTO = dnevnoStanjeService.save(dnevnoStanjeDTO)
        return ResponseEntity
            .created(URI("/api/dnevna-stanja" + result.id))
            .body(result)
    }

    @PutMapping("/{id}")
    @Throws(URISyntaxException::class)
    fun updateDnevnoStanje(
        @PathVariable(value = "id", required = false) id: Long,
        @RequestBody dnevnoStanjeDTO: DnevnoStanjeDTO
    ): ResponseEntity<DnevnoStanjeDTO> {
        log.debug("REST request to update DnevnoStanje : {}, {}", id, dnevnoStanjeDTO)
        if (dnevnoStanjeDTO.id == null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        if (id != dnevnoStanjeDTO.id) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        if (!dnevnoStanjeRepository.existsById(id)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        val result: DnevnoStanjeDTO = dnevnoStanjeService.save(dnevnoStanjeDTO)
        return ok()
            .body(result)
    }

    @GetMapping("/{id}")
    fun getDnevnoStanje(@PathVariable id: Long): ResponseEntity<DnevnoStanjeDTO> {
        log.debug("REST request to get DnevnoStanje : {}", id)
        val dnevnoStanjeDTO: Optional<DnevnoStanjeDTO> = dnevnoStanjeService.findOne(id)
        return if (dnevnoStanjeDTO.isEmpty) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else ok()
            .body(dnevnoStanjeDTO.get())
    }

    @DeleteMapping("/{id}")
    fun deleteDnevnoStanje(@PathVariable id: Long): ResponseEntity<Void> {
        log.debug("REST request to delete DnevnoStanje : {}", id)
        dnevnoStanjeService.delete(id)
        return ResponseEntity
            .noContent()
            .build()
    }

    @PostMapping("/upload")
    @Throws(IOException::class)
    fun uploadDnevnoStanjeFile(@RequestParam("fajl") fajl: MultipartFile): ResponseEntity<Unit> {
        return try {
            dnevnoStanjeService.uploadFile(fajl)
            ok().build()
        }catch (exception: Exception) {
            badRequest().build()
        }
    }
}