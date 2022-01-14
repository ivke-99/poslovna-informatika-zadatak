package pi.likvidatura.web.rest

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pi.likvidatura.repository.BankaRepository
import pi.likvidatura.service.dto.BankaDTO
import pi.likvidatura.service.impl.BankaService
import java.net.URI
import java.net.URISyntaxException
import java.util.*

/**
 * REST controller for managing [pi.likvidatura.domain.Banka].
 */
@CrossOrigin
@RestController
@RequestMapping("/api/banke")
class BankaController(private val bankaService: BankaService,
                      private val bankaRepository: BankaRepository) {

    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(BankaController::class.java)
    }

    @PostMapping
    @Throws(URISyntaxException::class)
    fun createBanka(@RequestBody bankaDTO: BankaDTO): ResponseEntity<BankaDTO> {
        log.debug("REST request to save Banka : {}", bankaDTO)
        if (bankaDTO.id != null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        val result: BankaDTO = bankaService.save(bankaDTO)
        return ResponseEntity
            .created(URI("/api/banke/" + result.id))
            .body(result)
    }

    @PutMapping("/{id}")
    @Throws(URISyntaxException::class)
    fun updateBanka(
        @PathVariable(value = "id", required = true) id: Long,
        @RequestBody bankaDTO: BankaDTO
    ): ResponseEntity<BankaDTO> {
        if (bankaDTO.id == null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        if (id != bankaDTO.id) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        if (!bankaRepository.existsById(id)) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        val result: BankaDTO = bankaService.save(bankaDTO)
        return ResponseEntity
            .ok()
            .body(result)
    }

    @GetMapping
    fun allBanke(): List<BankaDTO> = bankaService.findAll()

    @GetMapping("/{id}")
    fun getBanka(@PathVariable id: Long): ResponseEntity<BankaDTO> {
        val bankaDTO = bankaService.findOne(id)
        return if (bankaDTO.isEmpty) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else ResponseEntity
            .ok()
            .body(bankaDTO.get())
    }

    @DeleteMapping("/{id}")
    fun deleteBanka(@PathVariable id: Long): ResponseEntity<Void> {
        bankaService.delete(id)
        return ResponseEntity
            .noContent()
            .build()
    }
}