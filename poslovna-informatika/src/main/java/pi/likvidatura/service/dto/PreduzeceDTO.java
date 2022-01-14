package pi.likvidatura.service.dto;

import pi.likvidatura.domain.Preduzece;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link pi.likvidatura.domain.Preduzece} entity.
 */
public class PreduzeceDTO implements Serializable {

    private Long id;

    private String naziv;

    private String pib;

    private String maticniBroj;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getMaticniBroj() {
        return maticniBroj;
    }

    public void setMaticniBroj(String maticniBroj) {
        this.maticniBroj = maticniBroj;
    }

    public PreduzeceDTO(Long id, String naziv, String pib, String maticniBroj) {
        this.id = id;
        this.naziv = naziv;
        this.pib = pib;
        this.maticniBroj = maticniBroj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PreduzeceDTO)) {
            return false;
        }

        PreduzeceDTO preduzeceDTO = (PreduzeceDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, preduzeceDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PreduzeceDTO{" +
                "id=" + getId() +
                ", naziv='" + getNaziv() + "'" +
                ", pib='" + getPib() + "'" +
                ", maticniBroj='" + getMaticniBroj() + "'" +
                "}";
    }

    public static PreduzeceDTO fromEntity(Preduzece preduzece) {
        return new PreduzeceDTO(preduzece.getId(), preduzece.getNaziv(),
                preduzece.getPib(), preduzece.getMaticniBroj());
    }
}