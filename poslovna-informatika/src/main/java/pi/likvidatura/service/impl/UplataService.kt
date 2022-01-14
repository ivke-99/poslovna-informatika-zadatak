package pi.likvidatura.service.impl

import pi.likvidatura.repository.UplataRepository
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import pi.likvidatura.domain.Uplata
import pi.likvidatura.repository.IzlaznaFakturaRepository
import pi.likvidatura.repository.StavkaIzvodaRepository
import pi.likvidatura.service.dto.UplataDTO
import pi.likvidatura.service.mapper.IzlaznaFakturaMapper
import pi.likvidatura.service.mapper.StavkaIzvodaMapper
import pi.likvidatura.service.mapper.UplataMapper
import java.util.*
import java.util.stream.Collectors

@Service
class UplataService(
    private val stavkaIzvodaRepository: StavkaIzvodaRepository,
    private val izlaznaFakturaRepository: IzlaznaFakturaRepository,
    private val uplataRepository: UplataRepository

) {

    fun zatvoriFakturu(iznos: Double, stavkaId: Long, fakturaId: Long): String {
        var stavka = stavkaIzvodaRepository.findById(stavkaId).get()
        var faktura = izlaznaFakturaRepository.findById(fakturaId).get()
        val raspolozivo = stavka.iznos - stavka.iskorisceniIznos
        if(iznos <= 0) {
            return "Payment value must be larger than 0."
        }
        else if(faktura.isZatvorena) {
            return "Invoice is closed. Cannot make a payment."
        }
        else if(stavka.iznos == stavka.iskorisceniIznos) {
            return "All money already used from the unit."
        }
        else if(raspolozivo < iznos) {
            return "Amount available for payment is smaller than requested."
        }
        else{
            stavka.iskorisceniIznos = stavka.iskorisceniIznos + iznos
            faktura.isplaceniIznos = faktura.isplaceniIznos + iznos
            faktura.iznosZaPlacanje = faktura.iznosZaPlacanje - iznos
            if (faktura.isplaceniIznos == faktura.iznosZaPlacanje) {
                faktura.isZatvorena = true
            }
            stavka = stavkaIzvodaRepository.save(stavka)
            faktura = izlaznaFakturaRepository.save(faktura)
            uplataRepository.save(Uplata(iznos, stavka, faktura))
            return "Payment success"
        }

    }

    fun findAll(fakturaId: Long, stavkaId: Long, pageNum: Int): Page<UplataDTO> {
        lateinit var uplate: Page<UplataDTO>
        if(fakturaId != -1L && stavkaId != -1L) {
            uplate = uplataRepository
                .findAllByStavkaIzvodaIdAndFakturaId(fakturaId, stavkaId, PageRequest.of(pageNum, 10))
                .map { zatvaranje: Uplata? -> UplataDTO.fromEntity(zatvaranje) }
        }
        if(fakturaId == -1L && stavkaId != -1L) {
            uplate = uplataRepository
                .findAllByStavkaIzvodaId(stavkaId, PageRequest.of(pageNum, 10))
                .map { zatvaranje: Uplata? -> UplataDTO.fromEntity(zatvaranje) }
        }
        if(fakturaId != -1L && stavkaId == -1L) {
            uplate = uplataRepository
                .findAllByFakturaId(fakturaId, PageRequest.of(pageNum, 10))
                .map { zatvaranje: Uplata? -> UplataDTO.fromEntity(zatvaranje) }
        }
        if(fakturaId == -1L && stavkaId == -1L) {
            uplate = uplataRepository
                .findAll(PageRequest.of(pageNum, 10))
                .map { zatvaranje: Uplata? -> UplataDTO.fromEntity(zatvaranje) }
        }
        return uplate
    }
}