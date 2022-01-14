package pi.likvidatura.service.dto;

import pi.likvidatura.domain.StavkaIzvoda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link pi.likvidatura.domain.StavkaIzvoda} entity.
 */
public class StavkaIzvodaDTO implements Serializable {

    private Long id;

    private Integer brojStavke;

    private Double iznos;

    private Double iskorisceniIznos;

    private String duznik;

    private String svrhaPlacanja;

    private String primalac;

    private String racunDuznika;

    private String racunPrimaoca;

    private Integer model;

    private String pozivNaBroj;

    private DnevnoStanjeDTO dnevnoStanje;

    public StavkaIzvodaDTO(Long id, Integer brojStavke,
                           Double iznos, String duznik, String svrhaPlacanja,
                           String primalac, String racunDuznika, String racunPrimaoca,
                           Integer model, String pozivNaBroj, DnevnoStanjeDTO dnevnoStanje, Double iskorisceniIznos) {
        this.id = id;
        this.brojStavke = brojStavke;
        this.iznos = iznos;
        this.duznik = duznik;
        this.svrhaPlacanja = svrhaPlacanja;
        this.primalac = primalac;
        this.racunDuznika = racunDuznika;
        this.racunPrimaoca = racunPrimaoca;
        this.model = model;
        this.pozivNaBroj = pozivNaBroj;
        this.dnevnoStanje = dnevnoStanje;
        this.iskorisceniIznos = iskorisceniIznos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBrojStavke() {
        return brojStavke;
    }

    public void setBrojStavke(Integer brojStavke) {
        this.brojStavke = brojStavke;
    }

    public Double getIznos() {
        return iznos;
    }

    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }

    public String getDuznik() {
        return duznik;
    }

    public void setDuznik(String duznik) {
        this.duznik = duznik;
    }

    public String getSvrhaPlacanja() {
        return svrhaPlacanja;
    }

    public void setSvrhaPlacanja(String svrhaPlacanja) {
        this.svrhaPlacanja = svrhaPlacanja;
    }

    public String getPrimalac() {
        return primalac;
    }

    public void setPrimalac(String primalac) {
        this.primalac = primalac;
    }

    public String getRacunDuznika() {
        return racunDuznika;
    }

    public void setRacunDuznika(String racunDuznika) {
        this.racunDuznika = racunDuznika;
    }

    public String getRacunPrimaoca() {
        return racunPrimaoca;
    }

    public void setRacunPrimaoca(String racunPrimaoca) {
        this.racunPrimaoca = racunPrimaoca;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public String getPozivNaBroj() {
        return pozivNaBroj;
    }

    public void setPozivNaBroj(String pozivNaBroj) {
        this.pozivNaBroj = pozivNaBroj;
    }

    public DnevnoStanjeDTO getDnevnoStanje() {
        return dnevnoStanje;
    }

    public void setDnevnoStanje(DnevnoStanjeDTO dnevnoStanje) {
        this.dnevnoStanje = dnevnoStanje;
    }

    public Double getIskorisceniIznos() {
        return iskorisceniIznos;
    }

    public void setIskorisceniIznos(Double iskorisceniIznos) {
        this.iskorisceniIznos = iskorisceniIznos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StavkaIzvodaDTO)) {
            return false;
        }

        StavkaIzvodaDTO stavkaIzvodaDTO = (StavkaIzvodaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, stavkaIzvodaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StavkaIzvodaDTO{" +
            "id=" + getId() +
            ", brojStavke=" + getBrojStavke() +
            ", iznos=" + getIznos() +
            ", duznik='" + getDuznik() + "'" +
            ", svrhaPlacanja='" + getSvrhaPlacanja() + "'" +
            ", primalac='" + getPrimalac() + "'" +
            ", racunDuznika='" + getRacunDuznika() + "'" +
            ", racunPrimaoca='" + getRacunPrimaoca() + "'" +
            ", model=" + getModel() +
            ", pozivNaBroj='" + getPozivNaBroj() + "'" +
            ", dnevnoStanje=" + getDnevnoStanje() +
            "}";
    }

    public static StavkaIzvodaDTO fromEntity(StavkaIzvoda stavka) {
        return new StavkaIzvodaDTO(stavka.getId(), stavka.getBrojStavke(), stavka.getIznos(),
                stavka.getDuznik(), stavka.getSvrhaPlacanja(), stavka.getPrimalac(),
                stavka.getRacunDuznika(), stavka.getRacunPrimaoca(),
                stavka.getModel(), stavka.getPozivNaBroj(),
                DnevnoStanjeDTO.fromEntity(stavka.getDnevnoStanje()),
                stavka.getIskorisceniIznos());
    }

    public static List<StavkaIzvodaDTO> fromEntityList(List<StavkaIzvoda> stavke) {
        List<StavkaIzvodaDTO> dtos = new ArrayList<>();

        for(StavkaIzvoda stavka : stavke) {
            dtos.add(fromEntity(stavka));
        }

        return dtos;
    }
}
