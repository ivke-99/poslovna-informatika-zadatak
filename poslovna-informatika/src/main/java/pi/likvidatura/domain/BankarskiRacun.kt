package pi.likvidatura.domain

import java.io.Serializable
import java.util.function.Consumer
import javax.persistence.*

@Entity
@Table(name = "bankarski_racun")
class BankarskiRacun (

    @Column(name = "broj_racuna")
    var brojRacuna: String,

    @OneToMany(mappedBy = "bankarskiRacun")
    private var dnevnaStanja: MutableSet<DnevnoStanje> = hashSetOf(),

    @ManyToOne
    var preduzece: Preduzece,

    @ManyToOne
    var banka: Banka,
)
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long = 0
}
