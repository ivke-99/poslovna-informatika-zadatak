import * as React from 'react';
import Box from '@mui/material/Box';
import Alert from '@mui/material/Alert';
import IconButton from '@mui/material/IconButton';
import Collapse from '@mui/material/Collapse';
import {useState} from 'react';
import Button from '@mui/material/Button';
import CloseIcon from '@mui/icons-material/Close';

export default function DialogAlert({alertText}) {
    const [open, setOpen] = useState(true)
    return (
        <Collapse in={open}>
            <Alert severity="success"
                action={
                    <IconButton
                        aria-label="close"
                        color="inherit"
                        size="small"
                        onClick={() => {
                            setOpen(false);
                        }}
                    >
                        <CloseIcon fontSize="inherit" />
                    </IconButton>
                }
                sx={{ m:2, width: 200 }}
            >
                {alertText}
            </Alert>
        </Collapse>
    );
}