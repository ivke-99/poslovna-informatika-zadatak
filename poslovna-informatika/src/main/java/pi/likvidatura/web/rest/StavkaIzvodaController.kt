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
class StavkaIzvodaController(private val stavkaIzvodaService: StavkaIzvodaService) {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(StavkaIzvodaController::class.java)
    }

    @GetMapping
    fun getAllStavkeIzvoda(
        @RequestParam(defaultValue = "") svrhaPlacanja: String?,
        @RequestParam(defaultValue = "0") pageNum: Int
    ): Page<StavkaIzvodaDTO> {
        log.debug("REST request to get all")
        return stavkaIzvodaService.findAll(svrhaPlacanja ?: "", pageNum)
    }
}