package pi.likvidatura.web.rest

import com.itextpdf.text.DocumentException
import org.slf4j.LoggerFactory
import org.springframework.core.io.InputStreamResource
import org.springframework.data.domain.Page
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pi.likvidatura.repository.IzlaznaFakturaRepository
import pi.likvidatura.service.dto.IzlaznaFakturaDTO
import pi.likvidatura.service.impl.IzlaznaFakturaService
import java.io.FileNotFoundException
import java.net.URI
import java.net.URISyntaxException
import java.util.*

/**
 * REST controller for managing [pi.likvidatura.domain.IzlaznaFaktura].
 */
@CrossOrigin
@RestController
@RequestMapping("/api/izlazne-fakture")
class IzlaznaFakturaController(
    private val izlaznaFakturaService: IzlaznaFakturaService,
    private val izlaznaFakturaRepository: IzlaznaFakturaRepository
) {

    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(IzlaznaFakturaController::class.java)
    }

    @PostMapping
    @Throws(URISyntaxException::class)
    fun createIzlaznaFaktura(@RequestBody izlaznaFakturaDTO: IzlaznaFakturaDTO): ResponseEntity<IzlaznaFakturaDTO> {
        log.debug("REST request to save IzlaznaFaktura : {}", izlaznaFakturaDTO)
        if (izlaznaFakturaDTO.id != null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        val result: IzlaznaFakturaDTO = izlaznaFakturaService.save(izlaznaFakturaDTO)
        return ResponseEntity
            .created(URI("/api/izlazne-fakture/" + result.id))
            .body(result)
    }

    @PutMapping("/{id}")
    @Throws(URISyntaxException::class)
    fun updateIzlaznaFaktura(
        @PathVariable(value = "id", required = false) id: Long,
        @RequestBody izlaznaFakturaDTO: IzlaznaFakturaDTO
    ): ResponseEntity<IzlaznaFakturaDTO> {
        log.debug("REST request to update IzlaznaFaktura : {}, {}", id, izlaznaFakturaDTO)
        if (izlaznaFakturaDTO.id == null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        if (id != izlaznaFakturaDTO.id) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        if (!izlaznaFakturaRepository.existsById(id)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        val result: IzlaznaFakturaDTO = izlaznaFakturaService.save(izlaznaFakturaDTO)
        return ResponseEntity
            .ok()
            .body(result)
    }

    @GetMapping
    fun getAllIzlazneFakture(
        @RequestParam(defaultValue = "") brojFakture: String?,
        @RequestParam(defaultValue = "0") pageNum: Int
    ): Page<IzlaznaFakturaDTO> {
        log.debug("REST request to get all")
        return izlaznaFakturaService.findAll(brojFakture ?: "", pageNum)
    }

    @GetMapping("/{id}")
    fun getIzlaznaFaktura(@PathVariable id: Long): ResponseEntity<IzlaznaFakturaDTO> {
        log.debug("REST request to get IzlaznaFaktura : {}", id)
        val izlaznaFakturaDTO: Optional<IzlaznaFakturaDTO> = izlaznaFakturaService.findOne(id)
        return if (izlaznaFakturaDTO.isEmpty) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        } else ResponseEntity
            .ok()
            .body(izlaznaFakturaDTO.get())
    }

    @DeleteMapping("/{id}")
    fun deleteIzlaznaFaktura(@PathVariable id: Long): ResponseEntity<Void> {
        log.debug("REST request to delete IzlaznaFaktura : {}", id)
        izlaznaFakturaService.delete(id)
        return ResponseEntity
            .noContent()
            .build()
    }

    @GetMapping("/pdf")
    @Throws(FileNotFoundException::class, DocumentException::class)
    fun getPdf(@RequestParam poslovniPartnerId: Long): ResponseEntity<Any?> {
        val pdf = izlaznaFakturaService.generatePdf(poslovniPartnerId)
        val headers = HttpHeaders()
        headers.contentLength = pdf.available().toLong()
        headers.contentType = MediaType.APPLICATION_PDF
        val isr = InputStreamResource(pdf)
        return ResponseEntity(isr, headers, HttpStatus.OK)
    }
}