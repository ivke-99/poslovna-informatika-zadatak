package pi.likvidatura.domain

import javax.persistence.*

@Entity
@Table(name = "poslovni_partner")
class PoslovniPartner (
    var naziv: String,
    var adresa: String,
    var telefon: String,
){
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long = 0
}