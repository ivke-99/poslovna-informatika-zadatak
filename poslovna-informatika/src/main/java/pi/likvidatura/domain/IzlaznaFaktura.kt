package pi.likvidatura.domain

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import java.io.Serializable
import javax.persistence.*

/**
 * A IzlaznaFaktura.
 */
@Entity
@Table(name = "izlazna_faktura")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class IzlaznaFaktura (
    @Column(name = "broj_fakture")
    var brojFakture: String,

    @Column(name = "iznos_za_placanje")
    var iznosZaPlacanje: Double,
    var isplaceniIznos: Double,

    @ManyToOne
    var poslovnaGodina: PoslovnaGodina,

    @ManyToOne
    var poslovniPartner: PoslovniPartner,

    var isZatvorena: Boolean
)
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long = 0
}