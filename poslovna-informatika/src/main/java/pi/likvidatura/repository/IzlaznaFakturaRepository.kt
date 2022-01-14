package pi.likvidatura.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import pi.likvidatura.domain.IzlaznaFaktura

/**
 * Spring Data SQL repository for the IzlaznaFaktura entity.
 */
@Repository
interface IzlaznaFakturaRepository : JpaRepository<IzlaznaFaktura, Long> {
    fun findAllByBrojFaktureContaining(brojFakture: String, pageable: Pageable): Page<IzlaznaFaktura>
    fun findAllByBrojFaktureContaining(brojFakture: String): MutableList<IzlaznaFaktura>
    fun findAllByPoslovniPartnerId(poslovniPartnerId: Long): MutableList<IzlaznaFaktura>
}