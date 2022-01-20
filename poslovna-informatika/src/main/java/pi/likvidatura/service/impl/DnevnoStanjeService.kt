package pi.likvidatura.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import pi.likvidatura.domain.DnevnoStanje
import pi.likvidatura.repository.DnevnoStanjeRepository
import pi.likvidatura.repository.StavkaIzvodaRepository
import pi.likvidatura.service.dto.DnevnoStanjeDTO
import pi.likvidatura.service.mapper.DnevnoStanjeMapper
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import java.util.stream.Collectors

/**
 * Service Implementation for managing [DnevnoStanje].
 */
@Service
class DnevnoStanjeService(
    private val dnevnoStanjeRepository: DnevnoStanjeRepository,
    private val dnevnoStanjeMapper: DnevnoStanjeMapper,
    private val stavkaIzvodaRepository: StavkaIzvodaRepository
)
{
    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(DnevnoStanjeService::class.java)
    }

    fun save(dnevnoStanjeDTO: DnevnoStanjeDTO): DnevnoStanjeDTO {
        log.debug("Request to save DnevnoStanje : {}", dnevnoStanjeDTO)
        var dnevnoStanje = dnevnoStanjeMapper.toEntity(dnevnoStanjeDTO)
        dnevnoStanje = dnevnoStanjeRepository.save(dnevnoStanje)
        return dnevnoStanjeMapper.toDto(dnevnoStanje)
    }

    fun findAll(): List<DnevnoStanjeDTO> {
        log.debug("Request to get all")
        return dnevnoStanjeRepository.findAll().stream().map { s: DnevnoStanje? ->
            dnevnoStanjeMapper.toDto(
                s
            )
        }.collect(
            Collectors.toCollection { LinkedList() }
        )
    }

    fun findOne(id: Long): Optional<DnevnoStanjeDTO> {
        log.debug("Request to get DnevnoStanje : {}", id)
        return dnevnoStanjeRepository.findById(id).map { s: DnevnoStanje? ->
            dnevnoStanjeMapper.toDto(
                s
            )
        }
    }

    fun delete(id: Long) {
        log.debug("Request to delete DnevnoStanje : {}", id)
        dnevnoStanjeRepository.deleteById(id)
    }

    @Throws(IOException::class)
    fun uploadFile(file: MultipartFile) {
        val mapper = ObjectMapper().registerModule(JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        var stanje = mapper.readValue(convertMultiPartToFile(file), DnevnoStanje::class.java)
        var sacuvanoStanje = dnevnoStanjeRepository.save(stanje)

        for (stavka in stanje.stavkeIzvoda) {
            stavka.dnevnoStanje = sacuvanoStanje
            stavkaIzvodaRepository.save(stavka)
        }

    }

    @Throws(IOException::class, FileNotFoundException::class)
    private fun convertMultiPartToFile(file: MultipartFile): File {
        val convFile = File(file.originalFilename!!)
        val fos = FileOutputStream(convFile)
        fos.write(file.bytes)
        fos.close()
        return convFile
    }
}