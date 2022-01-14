import { useEffect, useState } from "react";
import { getPartners, getPdf } from "../api/apiCalls";
import { Button, MenuItem, Select, Stack } from "@mui/material";
import { Partner } from "./interface";

const DownloadPdf = () => {
  const [partners, setPartners] = useState<Partner[]>([]);
  const [partnerId, setPartnerId] = useState(0);

  useEffect(() => {
    getPartners().then((response) => {
      setPartners(response)
    }
    );
  }, []);

  async function generatePdf() {
    const pdf = await getPdf(partnerId);
    const filePdf = new Blob([pdf], { type: "application/pdf" });
    const fileUrl = URL.createObjectURL(filePdf);
    window.open(fileUrl, "_blank");
  }

  const onSelectValueChanged = (idValue: any) => {
    setPartnerId(idValue)
  }

  return (
    <div>
      <h3>Accounts Payable Ledger</h3>
      <h4>Please pick a business partner</h4>
      <Stack spacing={2} width={400}>
        <Select
          value={partnerId}
          fullWidth
          onChange={(e) => onSelectValueChanged(e.target.value)}
        >
          {partners.map((v) => (
            <MenuItem key={v.id} value={v.id}>{v.naziv}</MenuItem>
          ))}
        </Select>

        <Button variant='outlined' disabled={partnerId === 0} onClick={generatePdf}>
          Download PDF
        </Button>
      </Stack>
    </div>
  );
};

export default DownloadPdf;
