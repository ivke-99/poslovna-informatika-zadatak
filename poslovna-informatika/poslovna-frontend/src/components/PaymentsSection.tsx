import { useEffect, useState } from "react";
import { getAvailableCredit, getPayments, makePayment } from "../api/apiCalls";
import { Payment } from "./interface";
import { DataGrid, GridColDef } from '@mui/x-data-grid';
import { Button, Card, CardContent, Grid, TextField } from "@mui/material";
import { selectSelectedInvoice, selectSelectedUnit } from "../reducers/interfaceReducer";
import { useSelector } from "react-redux";


export default function PaymentSection() {
    const [payments, setPayments] = useState<Payment[]>([]);
    const reduxInvoice = useSelector(selectSelectedInvoice);
    const reduxUnit = useSelector(selectSelectedUnit);
    const [paymentValue, setPaymentValue] = useState(0);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalCount, setTotalCount] = useState(0);
    const [credit, setCredit] = useState(0);
    const [availableCredit, setAvailableCredit] = useState(0);

    const columns: GridColDef[] = [
        {
            field: "id",
            headerName: 'ID',
            width: 150,
            sortable: false,
            filterable: false,
        },
        {
            field: "iznos",
            headerName: "Amount",
            width: 150,
            sortable: false,
            filterable: false
        },
        {
            field: "faktura",
            headerName: "Invoice ID",
            width: 150,
            sortable: false,
            filterable: false,
            renderCell: (params) => (
                <div>{params.value.id}</div>
            ),
        },
        {
            field: "stavkaIzvoda",
            headerName: "Unit ID",
            width: 150,
            sortable: false,
            filterable: false,
            renderCell: (params) => (
                <div>{params.value.id}</div>
            ),
        },
    ];

    const onPaymentButtonClick = async () => {
        if(availableCredit < credit && credit !== 0) {
            alert("You don't have that much credit")
            return;
        }
        let dtoPayment = {
            iznos: paymentValue,
            fakturaId: reduxInvoice?.id,
            stavkaId: reduxUnit?.id,
            verzijaStavke: reduxUnit?.verzijaStavke,
            kreditZaKoriscenje: credit
        }
        makePayment(dtoPayment).then((response) => {
            alert(response.data)
            window.location.reload()
        })
    }



    useEffect(() => {
        getPayments(reduxInvoice?.id, reduxUnit?.id, currentPage).then((response) => {
            setPayments(response.content)
            setTotalCount(response.totalElements)
            if (reduxInvoice?.partnerId !== 0) {
                getAvailableCredit(reduxInvoice?.partnerId).then((response2) => {
                    setAvailableCredit(response2)
                })
            }
        })

    }, [currentPage, reduxInvoice, reduxUnit])

    return (
        <Grid container spacing={2} marginTop={5}>
            <Grid item xs={6}>
                <h4>Payments</h4>
                <DataGrid
                    autoHeight
                    rowHeight={40}
                    pageSize={10}
                    rowsPerPageOptions={[10]}
                    paginationMode="server"
                    rowCount={totalCount}
                    page={currentPage}
                    rows={payments}
                    columns={columns}
                    onPageChange={(page, details) => setCurrentPage(page)}
                />
            </Grid>
            {reduxInvoice?.id !== 0 && reduxUnit?.id !== 0 &&
                <Grid item xs={3}>
                    <Card sx={{ minWidth: 400, marginTop: 5 }}>
                        <CardContent>
                            <TextField
                                id="outlined-number"
                                label="Please enter the amount for payment"
                                type="number"
                                onChange={(e) => setPaymentValue(parseFloat(e.target.value))}
                                InputLabelProps={{
                                    shrink: true,
                                }}
                            />
                            <br></br>
                            {availableCredit !== 0 ?
                            <>
                            <h4>Available credit: {availableCredit}</h4>
                            <TextField
                                id="outlined-number"
                                margin="normal"
                                label="Use credit(optional)"
                                type="number"
                                onChange={(e) => setCredit(parseFloat(e.target.value))}
                                InputLabelProps={{
                                    shrink: true,
                                }}
                            />
                            </> : ""
                            }
                            <br></br>
                            <Button variant="contained" onClick={onPaymentButtonClick} disabled={paymentValue <= 0 && credit <= 0}>Make payment</Button>
                        </CardContent>
                    </Card>
                </Grid>
            }
        </Grid>
    )
};