package pi.likvidatura.domain
import javax.persistence.*

@Entity
@Table(name = "partner_kredit")
class PartnerKredit (
    var iznosKredita: Double,

    @OneToOne
    var poslovniPartner: PoslovniPartner,

)
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long = 0
}