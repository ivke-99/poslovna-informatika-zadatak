package pi.likvidatura.domain

import javax.persistence.*

@Entity
@Table(name = "poslovna_godina")
class PoslovnaGodina (

    @Column(name = "godina")
    var godina: Int,

    @Column(name = "zakljucena")
    var zakljucena: Boolean,

    @OneToMany(mappedBy = "poslovnaGodina")
    var izlaznaFakturas: MutableSet<IzlaznaFaktura> = mutableSetOf(),

    @ManyToOne
    var preduzece: Preduzece,
)
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long = 0
}