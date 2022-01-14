package pi.likvidatura.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pi.likvidatura.domain.Uplata

@Repository
interface UplataRepository : JpaRepository<Uplata, Long> {
    override fun findAll(pageable: Pageable): Page<Uplata>
    fun findAllByStavkaIzvodaIdAndFakturaId(stavkaIzvodaId: Long, fakturaId: Long, page: Pageable): Page<Uplata>
    fun findAllByStavkaIzvodaId(stavkaIzvodaId: Long, pageable: Pageable): Page<Uplata>
    fun findAllByFakturaId(fakturaId: Long, pageable: Pageable): Page<Uplata>
}