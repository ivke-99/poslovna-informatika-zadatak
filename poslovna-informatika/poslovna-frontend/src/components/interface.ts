export interface Partner {
    id: number;
    naziv: string;
}

export interface Invoice {
    id: number;
    brojFakture: string;
    iznosZaPlacanje: number;
    zatvorena: boolean;
}

export interface Unit {
    id: number;
    brojStavke: number;
    iznos: number;
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