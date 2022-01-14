package pi.likvidatura.domain

import javax.persistence.*

@Entity
@Table(name = "uplata")
class Uplata (
    var iznos : Double,

    @ManyToOne
    var stavkaIzvoda: StavkaIzvoda,

    @ManyToOne
    var faktura: IzlaznaFaktura,

){
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long = 0
}