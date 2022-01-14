package pi.likvidatura.domain

import java.io.Serializable
import java.time.LocalDate
import java.util.function.Consumer
import javax.persistence.*

@Entity
@Table(name = "dnevno_stanje")
class DnevnoStanje (

    @Column(name = "broj_izvoda")
    var brojIzvoda: Int,

    @Column(name = "datum_izvoda")
    var datumIzvoda: LocalDate,

    @Column(name = "prethodno_stanje")
    var prethodnoStanje: Double,

    @Column(name = "promet_u_korist")
    var prometUKorist: Double,

    @Column(name = "promet_na_teret")
    var prometNaTeret: Double,

    @Column(name = "novo_stanje")
    var novoStanje: Double,

    @Column(name = "rezervisano")
    var rezervisano: Double,

    @OneToMany(mappedBy = "dnevnoStanje")
    var stavkeIzvoda: MutableSet<StavkaIzvoda> = hashSetOf(),

    @ManyToOne
    var bankarskiRacun: BankarskiRacun,
){

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long = 0
}