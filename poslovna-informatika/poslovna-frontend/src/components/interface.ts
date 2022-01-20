export interface Partner {
    id: number;
    naziv: string;
}

export interface Invoice {
    id: number;
    brojFakture: string;
    iznosZaPlacanje: number;
    isplaceniIznos: number;
    zatvorena: boolean;
    partnerId: number;
}

export interface Unit {
    id: number;
    brojStavke: number;
    iznos: number;
    verzijaStavke: number;
    iskorisceniIznos: number;
    duznik: string;
    svrhaPlacanja: string;
    primalac: string;
}

export interface Payment {
    id: number;
    iznos: number;
    faktura: Invoice;
    stavka: Unit;
}