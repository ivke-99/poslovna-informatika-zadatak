package pi.likvidatura.service.dto;

import pi.likvidatura.domain.PoslovnaGodina;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link pi.likvidatura.domain.PoslovnaGodina} entity.
 */
public class PoslovnaGodinaDTO implements Serializable {

    private Long id;

    private Integer godina;

    private Boolean zakljucena;

    private PreduzeceDTO preduzece;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGodina() {
        return godina;
    }

    public void setGodina(Integer godina) {
        this.godina = godina;
    }

    public Boolean getZakljucena() {
        return zakljucena;
    }

    public void setZakljucena(Boolean zakljucena) {
        this.zakljucena = zakljucena;
    }

    public PreduzeceDTO getPreduzece() {
        return preduzece;
    }

    public void setPreduzece(PreduzeceDTO preduzece) {
        this.preduzece = preduzece;
    }

    public PoslovnaGodinaDTO(Long id, Integer godina, Boolean zakljucena, PreduzeceDTO preduzece) {
        this.id = id;
        this.godina = godina;
        this.zakljucena = zakljucena;
        this.preduzece = preduzece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PoslovnaGodinaDTO)) {
            return false;
        }

        PoslovnaGodinaDTO poslovnaGodinaDTO = (PoslovnaGodinaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, poslovnaGodinaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PoslovnaGodinaDTO{" +
                "id=" + getId() +
                ", godina=" + getGodina() +
                ", zakljucena='" + getZakljucena() + "'" +
                ", preduzece=" + getPreduzece() +
                "}";
    }

    public static PoslovnaGodinaDTO fromEntity(PoslovnaGodina godina) {
        return new PoslovnaGodinaDTO(godina.getId(), godina.getGodina(),
                godina.getZakljucena(), PreduzeceDTO.fromEntity(godina.getPreduzece()));
    }
}