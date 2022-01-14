package pi.likvidatura.service.dto;

import pi.likvidatura.domain.PoslovniPartner;

public class PoslovniPartnerDTO {

    private Long id;

    private String naziv;

    private String adresa;

    private String telefon;

    public PoslovniPartnerDTO() {}

    public PoslovniPartnerDTO(Long id, String naziv, String adresa, String telefon) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.telefon = telefon;
    }

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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public static PoslovniPartnerDTO fromEntity(PoslovniPartner partner) {
        return new PoslovniPartnerDTO(partner.getId(), partner.getNaziv(), partner.getAdresa(),
                partner.getTelefon());
    }
}
