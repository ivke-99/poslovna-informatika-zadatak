package pi.likvidatura.service.dto;

import pi.likvidatura.domain.Banka;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link pi.likvidatura.domain.Banka} entity.
 */
public class BankaDTO implements Serializable {

    private Long id;

    private String sifra;

    private String naziv;

    public BankaDTO(Long id, String sifra, String naziv) {
        this.id = id;
        this.sifra = sifra;
        this.naziv = naziv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankaDTO)) {
            return false;
        }

        BankaDTO bankaDTO = (BankaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, bankaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BankaDTO{" +
                "id=" + getId() +
                ", sifra='" + getSifra() + "'" +
                ", naziv='" + getNaziv() + "'" +
                "}";
    }

    public static BankaDTO fromEntity(Banka banka) {
        return new BankaDTO(banka.getId(), banka.getSifra(), banka.getNaziv());
    }
}
