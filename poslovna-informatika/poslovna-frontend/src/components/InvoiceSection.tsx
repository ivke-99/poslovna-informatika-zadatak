import { useEffect, useState } from "react";
import { getInvoices } from "../api/apiCalls";
import { Invoice } from "./interface";
import { DataGrid, GridColDef } from '@mui/x-data-grid';
import { Box, Button, Card, CardContent, Grid, TextField, Typography } from "@mui/material";
import { resetSelectedInvoice, selectSelectedInvoice, setSelectedInvoice } from "../reducers/interfaceReducer";
import { useDispatch, useSelector } from "react-redux";


export default function InvoiceSection() {
    const [invoices, setInvoices] = useState<Invoice[]>([]);
    const [searchTitle, setSearchTitle] = useState("");
    const reduxInvoice = useSelector(selectSelectedInvoice);
    const [selectedInvoice, setInvoice] = useState<Invoice>(reduxInvoice);
    const dispatch = useDispatch();
    const [currentPage, setCurrentPage] = useState(0);
    const [totalCount, setTotalCount] = useState(0);

    const columns: GridColDef[] = [
        {
            field: "id",
            headerName: 'ID',
            width: 150,
            sortable: false,
            filterable: false,
        },
        {
            field: "brojFakture",
            headerName: "Invoice Number",
            width: 150,
            sortable: false,
            filterable: false
        },
        {
            field: "iznosZaPlacanje",
            headerName: "Amount to pay",
            width: 150,
            sortable: false,
            filterable: false
        },
        {
            field: "isplaceniIznos",
            headerName: "Paid amount",
            width: 150,
            sortable: false,
            filterable: false
        },
        {
            field: "zatvorena",
            headerName: "Is closed",
            width: 150,
            sortable: false,
            filterable: false
        },
        {
            field: "poslovniPartner",
            headerName: "Partner ID",
            width: 150,
            sortable: false,
            filterable: false,
            renderCell: (params) => (
                <div>{params.value.id}</div>
            )
        },
    ];

    const onInvoiceClicked = (params: any) => {
        let invoice = {
            id: params.row.id,
            brojFakture: params.row.brojFakture,
            iznosZaPlacanje: params.row.iznosZaPlacanje,
            zatvorena: params.row.zatvorena,
            isplaceniIznos: params.row.isplaceniIznos,
            partnerId: params.row.poslovniPartner.id
        }
        setInvoice(invoice)
        dispatch(setSelectedInvoice(invoice))
    }

    const onResetSelected = () => {
        dispatch(resetSelectedInvoice())
        setInvoice({ id: 0, brojFakture: '', iznosZaPlacanje: 0, zatvorena: false, isplaceniIznos: 0, partnerId: 0 })
    }


    useEffect(() => {
        getInvoices(searchTitle, currentPage).then((response) => {
            setInvoices(response.content)
            setTotalCount(response.totalElements)
        })
    }, [searchTitle, currentPage])

    return (
        <Grid container spacing={2} marginTop={5}>
            <Grid item xs={6}>
                <Box width={400} marginLeft={40} marginBottom={0.5}>
                    <TextField id="standard-basic" label="Search by invoice number" variant="standard" onChange={(e) => setSearchTitle(e.target.value)} />
                </Box>
                <DataGrid
                    autoHeight
                    rowHeight={40}
                    pageSize={10}
                    rowsPerPageOptions={[10]}
                    paginationMode="server"
                    rowCount={totalCount}
                    page={currentPage}
                    rows={invoices}
                    columns={columns}
                    onRowClick={(params) => onInvoiceClicked(params)}
                    onPageChange={(page, details) => setCurrentPage(page)}
                />
            </Grid>
            {selectedInvoice.id !== 0 &&
                <Grid item xs={3}>
                    <Card sx={{ minWidth: 600, marginTop: 5 }}>
                        <CardContent>
                            <Typography variant="h4">
                                <u>Selected Invoice</u><br></br>
                                <b>ID:</b> {selectedInvoice?.id}<br></br>
                                <b>Invoice number:</b> {selectedInvoice.brojFakture}<br></br>
                                <b>Amount to pay:</b> {selectedInvoice.iznosZaPlacanje}
                            </Typography>
                            <Button size="large" variant='outlined' onClick={onResetSelected}>Reset</Button>
                        </CardContent>
                    </Card>
                </Grid>
            }
        </Grid>
    )
};