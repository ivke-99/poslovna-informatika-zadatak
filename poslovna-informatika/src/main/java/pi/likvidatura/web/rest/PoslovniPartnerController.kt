package pi.likvidatura.web.rest

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pi.likvidatura.service.dto.PoslovniPartnerDTO
import pi.likvidatura.service.impl.PoslovniPartnerService

@CrossOrigin
@RestController
@RequestMapping("/api/poslovni-partneri")
class PoslovniPartnerController(
    private val poslovniPartnerService: PoslovniPartnerService
) {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(PoslovniPartnerController::class.java)
    }

    @GetMapping
    fun allPartneri(): List<PoslovniPartnerDTO>
        {
            log.debug("REST request to get all")
            return poslovniPartnerService.getAll()
        }
}