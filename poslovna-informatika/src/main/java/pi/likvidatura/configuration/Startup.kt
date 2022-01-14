package pi.likvidatura.configuration

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import pi.likvidatura.domain.*
import pi.likvidatura.repository.*
import java.time.LocalDate

@Component
class Startup(
    private val bankaRepository: BankaRepository,
    private val preduzeceRepository: PreduzeceRepository,
    private val stavkaIzvodaRepository: StavkaIzvodaRepository,
    private val dnevnoStanjeRepository: DnevnoStanjeRepository,
    private val bankarskiRacunRepository: BankarskiRacunRepository,
    private val poslovniPartnerRepository: PoslovniPartnerRepository,
    private val poslovnaGodinaRepository: PoslovnaGodinaRepository,
    private val izlaznaFakturaRepository: IzlaznaFakturaRepository,
    private val uplataRepository: UplataRepository
): ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        var banka = Banka("G002","Slovenska banka")
        banka = bankaRepository.save(banka)

        var preduzece = Preduzece("NoSolutions", "45135135", "g242as22")
        preduzece = preduzeceRepository.save(preduzece)

        var bankarskiRacun = BankarskiRacun(
            "g44dd123", mutableSetOf(), preduzece, banka
        )
        bankarskiRacun = bankarskiRacunRepository.save(bankarskiRacun)

        var dnevnoStanje = DnevnoStanje(45, LocalDate.now(), 445.5, 12.4, 451.12, 631.53, 23.45,
            hashSetOf(), bankarskiRacun)

        var stavkaIzvoda = StavkaIzvoda(5, 555.55, 232.43, "Guras", "nema", "Bujanovic",
            "412521521", "43151351", 97, "42421-233-22", dnevnoStanje)
        var stavkaIzvoda2 = StavkaIzvoda(6, 424.42, 123.23, "Brawe", "fra", "Dasdad",
            "412521521", "43151351", 97, "42421-2341-22", dnevnoStanje)
        var stavkaIzvoda3 = StavkaIzvoda(7, 881.2, 23.34, "NMd", "pouzece", "Brassb",
            "123123123", "123123123", 97, "42421-233-99983", dnevnoStanje)

        dnevnoStanje.stavkeIzvoda = hashSetOf(stavkaIzvoda, stavkaIzvoda2, stavkaIzvoda3)

        dnevnoStanjeRepository.save(dnevnoStanje)
        stavkaIzvoda = stavkaIzvodaRepository.save(stavkaIzvoda)
        stavkaIzvoda2 = stavkaIzvodaRepository.save(stavkaIzvoda2)
        stavkaIzvoda3 = stavkaIzvodaRepository.save(stavkaIzvoda3)

        var poslovnaGodina = PoslovnaGodina(
            2021, false, mutableSetOf(), preduzece
        )
        var poslovniPartner = PoslovniPartner(
            "Neki partner", "Mikeladnesa 123", "05261213"
        )
        poslovniPartner = poslovniPartnerRepository.save(poslovniPartner)
        var izlaznaFaktura = IzlaznaFaktura(
            "g421412",555.23, 223.4, poslovnaGodina, poslovniPartner, false
        )
        var izlaznaFaktura2 = IzlaznaFaktura(
            "b4b141212",63161.23, 231.4, poslovnaGodina, poslovniPartner, false
        )
        var izlaznaFaktura3 = IzlaznaFaktura(
            "asga231",6777.23, 451.4, poslovnaGodina, poslovniPartner, false
        )
        poslovnaGodina.izlaznaFakturas = mutableSetOf(izlaznaFaktura, izlaznaFaktura2, izlaznaFaktura3)

        poslovnaGodinaRepository.save(poslovnaGodina)

        izlaznaFakturaRepository.save(izlaznaFaktura)
        izlaznaFakturaRepository.save(izlaznaFaktura2)
        izlaznaFakturaRepository.save(izlaznaFaktura3)
        var uplata = Uplata(545.34, stavkaIzvoda, izlaznaFaktura )
        var uplata2 = Uplata(566.55, stavkaIzvoda2, izlaznaFaktura2 )
        var uplata3 = Uplata(4443.3, stavkaIzvoda3, izlaznaFaktura3 )
        uplataRepository.save(uplata)
        uplataRepository.save(uplata2)
        uplataRepository.save(uplata3)

    }
}