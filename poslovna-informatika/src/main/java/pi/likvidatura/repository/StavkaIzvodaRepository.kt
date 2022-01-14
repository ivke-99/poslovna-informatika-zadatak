package pi.likvidatura.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import pi.likvidatura.domain.StavkaIzvoda

/**
 * Spring Data SQL repository for the StavkaIzvoda entity.
 */
@Repository
interface StavkaIzvodaRepository : JpaRepository<StavkaIzvoda, Long> {
    fun findAllBySvrhaPlacanjaContaining(svrhaPlacanja: String, pageable: Pageable): Page<StavkaIzvoda>
}