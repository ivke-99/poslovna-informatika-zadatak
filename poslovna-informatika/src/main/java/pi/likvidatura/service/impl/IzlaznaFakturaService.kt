package pi.likvidatura.service.impl

import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import com.lowagie.text.pdf.PdfTable
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pi.likvidatura.domain.IzlaznaFaktura
import pi.likvidatura.repository.IzlaznaFakturaRepository
import pi.likvidatura.repository.PoslovniPartnerRepository
import pi.likvidatura.service.dto.IzlaznaFakturaDTO
import pi.likvidatura.service.mapper.IzlaznaFakturaMapper
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.util.*

/**
 * Service Implementation for managing [IzlaznaFaktura].
 */
@Service
class IzlaznaFakturaService(
    private val izlaznaFakturaRepository: IzlaznaFakturaRepository,
    private val poslovniPartnerRepository: PoslovniPartnerRepository,
    private val izlaznaFakturaMapper: IzlaznaFakturaMapper

) {
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(IzlaznaFakturaService::class.java)
    }

    fun save(izlaznaFakturaDTO: IzlaznaFakturaDTO): IzlaznaFakturaDTO {
        log.debug("Request to save IzlaznaFaktura : {}", izlaznaFakturaDTO)
        var izlaznaFaktura = izlaznaFakturaMapper.toEntity(izlaznaFakturaDTO)
        izlaznaFaktura = izlaznaFakturaRepository.save(izlaznaFaktura)
        return izlaznaFakturaMapper.toDto(izlaznaFaktura)
    }

    fun findAll(brojFakture: String, pageNum: Int): Page<IzlaznaFakturaDTO> {
        return izlaznaFakturaRepository
            .findAllByBrojFaktureContaining(brojFakture, PageRequest.of(pageNum, 10))
            .map(IzlaznaFakturaDTO::fromEntity)
    }

    fun findOne(id: Long): Optional<IzlaznaFakturaDTO> {
        log.debug("Request to get IzlaznaFaktura : {}", id)
        return izlaznaFakturaRepository.findById(id).map { s: IzlaznaFaktura? ->
            izlaznaFakturaMapper.toDto(
                s
            )
        }
    }

    fun delete(id: Long) {
        log.debug("Request to delete IzlaznaFaktura : {}", id)
        izlaznaFakturaRepository.deleteById(id)
    }

    @Throws(DocumentException::class, FileNotFoundException::class)
    fun generatePdf(poslovniPartnerId: Long): ByteArrayInputStream {
        val fakturePartnera: List<IzlaznaFaktura> = izlaznaFakturaRepository.findAllByPoslovniPartnerId(poslovniPartnerId)
        val partner = poslovniPartnerRepository.findById(poslovniPartnerId)
        val font = FontFactory.getFont(FontFactory.COURIER, 14f, BaseColor.BLACK);
        val document = Document()
        val bos = ByteArrayOutputStream()
        PdfWriter.getInstance(document, bos)
        document.open()
        val header = Paragraph(
            "Accounts Payable Ledger for "
                    + partner.get().naziv
        , font)
        header.alignment = Element.ALIGN_CENTER
        document.add(header)
        document.add(Chunk.NEWLINE)

        var table = PdfPTable(2)
        for(headerTile in arrayOf("Invoice number", "Amount owed")) {
            var header = PdfPCell()
            val headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            header.backgroundColor = BaseColor.LIGHT_GRAY
            header.horizontalAlignment = Element.ALIGN_CENTER
            header.borderWidth = 2f
            header.phrase = Phrase(headerTile, headFont)
            table.addCell(header)
        }
        for (faktura: IzlaznaFaktura in fakturePartnera) {
            val invoicenumberCell = PdfPCell(Phrase(faktura.brojFakture))
            invoicenumberCell.paddingLeft = 4f
            invoicenumberCell.verticalAlignment = Element.ALIGN_MIDDLE
            invoicenumberCell.horizontalAlignment = Element.ALIGN_CENTER
            table.addCell(invoicenumberCell)

            val amountCell = PdfPCell(Phrase(faktura.iznosZaPlacanje.toString()))
            amountCell.paddingLeft = 4f
            amountCell.verticalAlignment = Element.ALIGN_MIDDLE
            amountCell.horizontalAlignment = Element.ALIGN_LEFT
            table.addCell(amountCell)

        }
            document.add(table)
            document.close()
            return ByteArrayInputStream(bos.toByteArray())

    }
}