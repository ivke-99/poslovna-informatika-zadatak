package pi.likvidatura.service.dto;


import pi.likvidatura.domain.Uplata;

public class UplataDTO {

    private Long id;

    private Double iznos;

    private StavkaIzvodaDTO stavkaIzvoda;

    private IzlaznaFakturaDTO faktura;

    public UplataDTO() {};

    public UplataDTO(Long id,
                                Double iznos,
                                StavkaIzvodaDTO stavkaIzvoda,
                                IzlaznaFakturaDTO faktura) {
        this.id = id;
        this.iznos = iznos;
        this.stavkaIzvoda = stavkaIzvoda;
        this.faktura = faktura;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getIznos() {
        return iznos;
    }

    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }

    public StavkaIzvodaDTO getStavkaIzvoda() {
        return stavkaIzvoda;
    }

    public void setStavkaIzvoda(StavkaIzvodaDTO stavkaIzvoda) {
        this.stavkaIzvoda = stavkaIzvoda;
    }

    public IzlaznaFakturaDTO getFaktura() {
        return faktura;
    }

    public void setFaktura(IzlaznaFakturaDTO faktura) {
        this.faktura = faktura;
    }

    public static UplataDTO fromEntity(Uplata uplata) {
        return new UplataDTO(uplata.getId(), uplata.getIznos(),
                StavkaIzvodaDTO.fromEntity(uplata.getStavkaIzvoda()),
                IzlaznaFakturaDTO.fromEntity(uplata.getFaktura()));
    }

}
