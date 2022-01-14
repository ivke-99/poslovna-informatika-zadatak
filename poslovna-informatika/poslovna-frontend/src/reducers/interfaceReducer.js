import { createSlice } from '@reduxjs/toolkit';

const initialState = {
    selectedInvoice: {
        id: 0,
        brojFakture: "",
        iznosZaPlacanje: "",
        zatvorena: false
    },
    selectedUnit: {
        id: 0,
        brojStavke: 0,
        iznos: 0,
        duznik: "",
        svrhaPlacanja: "",
        primalac: ""

    }
};

export const interfaceSlice = createSlice({
    name: 'interface',
    initialState,
    reducers: {
        setSelectedInvoice: (state, action) => {
            state.selectedInvoice = {...action.payload};
        },
        setSelectedUnit: (state, action) => {
            state.selectedUnit = {...action.payload};
        },
        resetSelectedInvoice: (state) => {
            state.selectedInvoice = initialState.selectedInvoice;
        },
        resetSelectedUnit: (state) => {
            state.selectedUnit = initialState.selectedUnit;
        },
    },
});

export const {
    setSelectedInvoice,
    setSelectedUnit,
    resetSelectedInvoice,
    resetSelectedUnit,
} = interfaceSlice.actions;

export const selectSelectedInvoice = (state) => state.interface.selectedInvoice;
export const selectSelectedUnit = (state) => state.interface.selectedUnit;

export default interfaceSlice.reducer;
