package pi.likvidatura.domain

import java.io.Serializable
import java.util.function.Consumer
import javax.persistence.*

@Entity
@Table(name = "preduzece")
class Preduzece (

    @Column(name = "naziv")
    var naziv: String,

    @Column(name = "pib")
    var pib: String,

    @Column(name = "maticni_broj")
    var maticniBroj: String,

    @OneToMany(mappedBy = "preduzece")
    private var bankarskiRacuns: MutableSet<BankarskiRacun> = mutableSetOf(),

    @OneToMany(mappedBy = "preduzece")
    private var poslovnaGodinas: MutableSet<PoslovnaGodina>? = mutableSetOf(),
){
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long = 0
}