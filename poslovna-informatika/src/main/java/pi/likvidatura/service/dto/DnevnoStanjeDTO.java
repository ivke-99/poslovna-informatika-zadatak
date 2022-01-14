package pi.likvidatura.service.dto;

import pi.likvidatura.domain.DnevnoStanje;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link pi.likvidatura.domain.DnevnoStanje} entity.
 */
public class DnevnoStanjeDTO implements Serializable {

    private Long id;

    private Integer brojIzvoda;

    private LocalDate datumIzvoda;

    private Double prethodnoStanje;

    private Double prometUKorist;

    private Double prometNaTeret;

    private Double novoStanje;

    private Double rezervisano;

    private BankarskiRacunDTO bankarskiRacun;

    public DnevnoStanjeDTO(Long id, Integer brojIzvoda,
                           LocalDate datumIzvoda, Double prethodnoStanje,
                           Double prometUKorist, Double prometNaTeret, Double novoStanje,
                           Double rezervisano, BankarskiRacunDTO bankarskiRacun) {
        this.id = id;
        this.brojIzvoda = brojIzvoda;
        this.datumIzvoda = datumIzvoda;
        this.prethodnoStanje = prethodnoStanje;
        this.prometUKorist = prometUKorist;
        this.prometNaTeret = prometNaTeret;
        this.novoStanje = novoStanje;
        this.rezervisano = rezervisano;
        this.bankarskiRacun = bankarskiRacun;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBrojIzvoda() {
        return brojIzvoda;
    }

    public void setBrojIzvoda(Integer brojIzvoda) {
        this.brojIzvoda = brojIzvoda;
    }

    public LocalDate getDatumIzvoda() {
        return datumIzvoda;
    }

    public void setDatumIzvoda(LocalDate datumIzvoda) {
        this.datumIzvoda = datumIzvoda;
    }

    public Double getPrethodnoStanje() {
        return prethodnoStanje;
    }

    public void setPrethodnoStanje(Double prethodnoStanje) {
        this.prethodnoStanje = prethodnoStanje;
    }

    public Double getPrometUKorist() {
        return prometUKorist;
    }

    public void setPrometUKorist(Double prometUKorist) {
        this.prometUKorist = prometUKorist;
    }

    public Double getPrometNaTeret() {
        return prometNaTeret;
    }

    public void setPrometNaTeret(Double prometNaTeret) {
        this.prometNaTeret = prometNaTeret;
    }

    public Double getNovoStanje() {
        return novoStanje;
    }

    public void setNovoStanje(Double novoStanje) {
        this.novoStanje = novoStanje;
    }

    public Double getRezervisano() {
        return rezervisano;
    }

    public void setRezervisano(Double rezervisano) {
        this.rezervisano = rezervisano;
    }

    public BankarskiRacunDTO getBankarskiRacun() {
        return bankarskiRacun;
    }

    public void setBankarskiRacun(BankarskiRacunDTO bankarskiRacun) {
        this.bankarskiRacun = bankarskiRacun;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DnevnoStanjeDTO)) {
            return false;
        }

        DnevnoStanjeDTO dnevnoStanjeDTO = (DnevnoStanjeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dnevnoStanjeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DnevnoStanjeDTO{" +
                "id=" + getId() +
                ", brojIzvoda=" + getBrojIzvoda() +
                ", datumIzvoda='" + getDatumIzvoda() + "'" +
                ", prethodnoStanje=" + getPrethodnoStanje() +
                ", prometUKorist=" + getPrometUKorist() +
                ", prometNaTeret=" + getPrometNaTeret() +
                ", novoStanje=" + getNovoStanje() +
                ", rezervisano=" + getRezervisano() +
                ", bankarskiRacun=" + getBankarskiRacun() +
                "}";
    }

    public static DnevnoStanjeDTO fromEntity(DnevnoStanje stanje) {
        return new DnevnoStanjeDTO(stanje.getId(), stanje.getBrojIzvoda(),
                stanje.getDatumIzvoda(), stanje.getPrethodnoStanje(), stanje.getPrometUKorist(),
                stanje.getPrometNaTeret(), stanje.getNovoStanje(), stanje.getRezervisano(),
                BankarskiRacunDTO.fromEntity(stanje.getBankarskiRacun()));
    }
}