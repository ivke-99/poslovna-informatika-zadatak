package pi.likvidatura.service.impl

import pi.likvidatura.repository.UplataRepository
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pi.likvidatura.domain.PartnerKredit
import pi.likvidatura.domain.Uplata
import pi.likvidatura.repository.IzlaznaFakturaRepository
import pi.likvidatura.repository.PartnerKreditRepository
import pi.likvidatura.repository.StavkaIzvodaRepository
import pi.likvidatura.service.dto.UplataDTO
import pi.likvidatura.service.mapper.IzlaznaFakturaMapper
import pi.likvidatura.service.mapper.StavkaIzvodaMapper
import pi.likvidatura.service.mapper.UplataMapper
import java.util.*
import java.util.stream.Collectors

@Service
open// TODO transactional
class UplataService(
    private val stavkaIzvodaRepository: StavkaIzvodaRepository,
    private val izlaznaFakturaRepository: IzlaznaFakturaRepository,
    private val uplataRepository: UplataRepository,
    private val partnerKreditRepository: PartnerKreditRepository

) {
    fun zatvoriFakturu(iznos: Double, stavkaId: Long, fakturaId: Long, verzijaStavke: Int, kreditZaKoriscenje: Double): String {
        var stavka = stavkaIzvodaRepository.findById(stavkaId).get()
        var faktura = izlaznaFakturaRepository.findById(fakturaId).get()
        val raspolozivo = stavka.iznos - stavka.iskorisceniIznos
        val isAvailable = partnerKreditRepository.findByPoslovniPartnerId(faktura.poslovniPartner.id)
        if (isAvailable != null && kreditZaKoriscenje != 0.00) {
            if (isAvailable.iznosKredita < kreditZaKoriscenje) {
                return "You don't have that much credit or credit doesen't exist."
            }
        }
        if(verzijaStavke != stavka.verzijaStavke) {
            return "Unit was updated during your payment. Please check the unit, and confirm your payment again."
        }
        if(iznos <= 0 && kreditZaKoriscenje <= 0) {
            return "Payment value must be larger than 0."
        }
        else if(faktura.isZatvorena) {
            return "Invoice is closed. Cannot make a payment."
        }
        else if(stavka.iznos <= stavka.iskorisceniIznos) {
            println(stavka.iznos)
            println(stavka.iskorisceniIznos)
            return "All money already used from the unit."
        }
        else if(raspolozivo < iznos) {
            return "Amount available for payment is smaller than requested."
        }
        else{
            var preostalo = 0.00
            stavka.iskorisceniIznos = stavka.iskorisceniIznos + iznos
            faktura.isplaceniIznos = faktura.isplaceniIznos + iznos + kreditZaKoriscenje
            faktura.iznosZaPlacanje = faktura.iznosZaPlacanje - iznos - kreditZaKoriscenje
            if (faktura.isplaceniIznos >= faktura.iznosZaPlacanje) {
                faktura.isZatvorena = true
                preostalo = faktura.iznosZaPlacanje - faktura.isplaceniIznos
                if(preostalo < 0) {
                    preostalo *= -1
                }
            }

            if(preostalo > 0 || kreditZaKoriscenje > 0) {
                var kredit = partnerKreditRepository.findByPoslovniPartnerId(faktura.poslovniPartner.id)
                if(kredit != null) {
                    kredit.iznosKredita = kredit.iznosKredita + preostalo - kreditZaKoriscenje
                    partnerKreditRepository.save(kredit)
                }
                else{
                    partnerKreditRepository.save(PartnerKredit(preostalo, faktura.poslovniPartner))
                }
            }
            stavka = stavkaIzvodaRepository.save(stavka)
            faktura = izlaznaFakturaRepository.save(faktura)
            uplataRepository.save(Uplata(iznos + kreditZaKoriscenje, stavka, faktura))
            return "Payment success"
        }

    }

    fun findAll(fakturaId: Long, stavkaId: Long, pageNum: Int): Page<UplataDTO> {
        lateinit var uplate: Page<UplataDTO>
        if(fakturaId != -1L && stavkaId != -1L) {
            uplate = uplataRepository
                .findAllByStavkaIzvodaIdAndFakturaId(stavkaId, fakturaId, PageRequest.of(pageNum, 10))
                .map { uplata: Uplata? -> UplataDTO.fromEntity(uplata) }
        }
        if(fakturaId == -1L && stavkaId != -1L) {
            uplate = uplataRepository
                .findAllByStavkaIzvodaId(stavkaId, PageRequest.of(pageNum, 10))
                .map { uplata: Uplata? -> UplataDTO.fromEntity(uplata) }
        }
        if(fakturaId != -1L && stavkaId == -1L) {
            uplate = uplataRepository
                .findAllByFakturaId(fakturaId, PageRequest.of(pageNum, 10))
                .map { uplata: Uplata? -> UplataDTO.fromEntity(uplata) }
        }
        if(fakturaId == -1L && stavkaId == -1L) {
            uplate = uplataRepository
                .findAll(PageRequest.of(pageNum, 10))
                .map { uplata: Uplata? -> UplataDTO.fromEntity(uplata) }
        }
        return uplate
    }
}