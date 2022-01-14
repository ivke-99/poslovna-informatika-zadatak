package pi.likvidatura.web.rest

import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pi.likvidatura.service.dto.UplataDTO
import pi.likvidatura.service.impl.UplataService
import java.net.URI
import java.net.URISyntaxException
import java.util.*

@CrossOrigin
@RestController
@RequestMapping("/api/uplate")
class UplataController(
    private val uplataService: UplataService
) {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(UplataController::class.java)
    }

    @GetMapping
    fun getAllUplata(
        @RequestParam(required = false, defaultValue = "-1") fakturaId: Long,
        @RequestParam(required = false, defaultValue = "-1") stavkaId: Long,
        @RequestParam(required = false, defaultValue = "0") pageNum: Int,
    ): Page<UplataDTO> {
        log.debug("REST request to get all")
        return uplataService.findAll(fakturaId, stavkaId, pageNum)
    }

    @PostMapping
    @Throws(URISyntaxException::class)
    fun uplatiFakturu(@RequestBody paymentDTO: PaymentDTO): ResponseEntity<*> {
        val result = uplataService.zatvoriFakturu(paymentDTO.iznos, paymentDTO.stavkaId, paymentDTO.fakturaId)
        return ResponseEntity
            .ok()
            .body(result)
    }
}

data class PaymentDTO(var iznos: Double, var fakturaId: Long, var stavkaId: Long)