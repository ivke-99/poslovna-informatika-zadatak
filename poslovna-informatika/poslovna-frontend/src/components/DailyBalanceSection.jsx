import { useState } from "react";
import { uploadDailyReport } from "../api/apiCalls";
import { Button, Input, Stack } from "@mui/material";
import DialogAlert from "./core/DialogAlert";

const DownloadPdf = () => {
  const [file, setFile] = useState(null);
  const [alertText, setAlertText] = useState('');

  async function upload() {
    await uploadDailyReport(file).then((response) => {
        setFile(null);
        if(response.status === 200) {
          setAlertText("Upload success!")
        }
        else{
          setAlertText("Upload failure. Please check if the file is in correct format.")
        }
    })    
  }

  return (
    <div>
      <h3>Import daily balance</h3>
      <h4>Please pick a file</h4>
      <Stack spacing={2} width={400}>
      <Input id="contained-button-file" type="file" multipart="true" onChange={(e) => setFile(e.target.files[0])}/>
        <Button disabled={file === null} variant="contained" component="span" onClick={upload}>
          Upload
        </Button>
      </Stack>
      {alertText !== '' && <DialogAlert alertText={alertText}/>}
    </div>
  );
};

export default DownloadPdf;
