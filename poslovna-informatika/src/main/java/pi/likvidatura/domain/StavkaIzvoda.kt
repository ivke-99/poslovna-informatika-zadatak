package pi.likvidatura.domain

import java.io.Serializable
import javax.persistence.*

/**
 * A StavkaIzvoda.
 */
@Entity
@Table(name = "stavka_izvoda")
class StavkaIzvoda (

    @Column(name = "broj_stavke")
    var brojStavke: Int,

    @Column(name = "iznos")
    var iznos: Double,

    @Column(name = "verzija")
    var verzijaStavke: Int = 0,

    @Column(name = "iskorisceni_iznos")
    var iskorisceniIznos: Double,

    @Column(name = "duznik")
    var duznik: String,

    @Column(name = "svrha_placanja")
    var svrhaPlacanja: String,

    @Column(name = "primalac")
    var primalac: String,

    @Column(name = "racun_duznika")
    var racunDuznika: String,

    @Column(name = "racun_primaoca")
    var racunPrimaoca: String,

    @Column(name = "model")
    var model: Int,

    @Column(name = "poziv_na_broj")
    var pozivNaBroj: String,

    @ManyToOne
    var dnevnoStanje: DnevnoStanje,
){
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long = 0
}