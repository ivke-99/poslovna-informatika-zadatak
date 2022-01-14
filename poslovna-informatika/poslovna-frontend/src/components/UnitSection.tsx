import { useEffect, useState } from "react";
import { getUnits } from "../api/apiCalls";
import { Unit } from "./interface";
import { DataGrid, GridColDef } from '@mui/x-data-grid';
import { Box, Button, Card, CardContent, Grid, TextField, Typography } from "@mui/material";
import { resetSelectedUnit, selectSelectedUnit, setSelectedUnit } from "../reducers/interfaceReducer";
import { useDispatch, useSelector } from "react-redux";


export default function UnitsSection(props: any) {
    const [units, setUnits] = useState<Unit[]>([]);
    const [searchTitle, setSearchTitle] = useState("");
    const reduxUnit = useSelector(selectSelectedUnit);
    const [selectedUnit, setUnit] = useState<Unit>(reduxUnit);
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
            field: "brojStavke",
            headerName: "Unit number",
            width: 150,
            sortable: false,
            filterable: false
        },
        {
            field: "iznos",
            headerName: "Quantity",
            width: 150,
            sortable: false,
            filterable: false
        },
        {
            field: "duznik",
            headerName: "Debtor",
            width: 150,
            sortable: false,
            filterable: false
        },
        {
            field: "svrhaPlacanja",
            headerName: "Reason of payment",
            width: 150,
            sortable: false,
            filterable: false
        },
        {
            field: "primalac",
            headerName: "Recipient",
            width: 150,
            sortable: false,
            filterable: false
        },
    ];

    const onUnitClicked = (params: any) => {
        let unit = {
            id: params.row.id,
            brojStavke: params.row.brojStavke,
            iznos: params.row.iznos,
            duznik: params.row.duznik,
            svrhaPlacanja: params.row.svrhaPlacanja,
            primalac: params.row.primalac
        }
        setUnit(unit)
        dispatch(setSelectedUnit(unit))
    }

    const onResetSelected = () => {
        dispatch(resetSelectedUnit())
        setUnit({
            id: 0,
            brojStavke: 0,
            iznos: 0,
            duznik: "",
            svrhaPlacanja: "",
            primalac: ""
        })
    }


    useEffect(() => {
        getUnits(searchTitle, currentPage).then((response) => {
            setUnits(response.content)
            setTotalCount(response.totalElements)
        })
    }, [searchTitle, currentPage])

    return (
        <Grid container spacing={2} marginTop={5}>
            <Grid item xs={6}>
                <Box width={300} marginLeft={40} marginBottom={0.5}>
                    <TextField id="standard-basic" label="Search by reason of payment" variant="standard" onChange={(e) => setSearchTitle(e.target.value)} />
                </Box>
                <DataGrid
                    autoHeight
                    rowHeight={40}
                    pageSize={10}
                    rowsPerPageOptions={[10]}
                    paginationMode="server"
                    rowCount={totalCount}
                    page={currentPage}
                    rows={units}
                    columns={columns}
                    onRowClick={(params) => onUnitClicked(params)}
                    onPageChange={(page, details) => setCurrentPage(page)}
                />
            </Grid>
            {selectedUnit.id !== 0 &&
                <Grid item xs={3}>
                    <Card sx={{ minWidth: 600, marginTop: 5 }}>
                        <CardContent>
                            <Typography variant="h4">
                                <u>Selected unit</u><br></br>
                                <b>ID:</b> {selectedUnit?.id}<br></br>
                                <b>Unit number:</b> {selectedUnit?.brojStavke}<br></br>
                                <b>Quantity:</b> {selectedUnit?.iznos}
                            </Typography>
                            <Button size="large" variant='outlined' onClick={onResetSelected}>Reset</Button>
                        </CardContent>
                    </Card>
                </Grid>
            }
        </Grid>
    )
};