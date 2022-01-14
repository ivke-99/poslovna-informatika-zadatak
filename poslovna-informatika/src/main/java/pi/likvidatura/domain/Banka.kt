package pi.likvidatura.domain

import javax.persistence.*

@Entity
@Table(name = "banka")
class Banka (

    @Column(name = "sifra")
    var sifra: String,

    @Column(name = "naziv")
    var naziv: String,

    @OneToMany(mappedBy = "banka")
    var bankarskiRacuns: MutableSet<BankarskiRacun> = hashSetOf(),
){
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long = 0
}