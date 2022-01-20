import axios from "axios";
const baseURL = "http://localhost:8080/api";

export async function getPartners() {
    const response = await axios.get(baseURL + "/poslovni-partneri");
    return response.data;
}

export async function uploadDailyReport(dailyReportFile: any) {
    var formData = new FormData();
    formData.append("fajl", dailyReportFile);
  
    const response = await axios.post(baseURL + "/dnevna-stanja/upload", formData);
    return response;
}

export async function getInvoices(searchTerm?: string, pageNum?: number) {
    const response = await axios.get(baseURL + "/izlazne-fakture", {
      params: {
        ...(searchTerm !== '' ? { brojFakture: searchTerm } : {}),
        ...(pageNum ? { pageNum: pageNum } : {}),
      },
    });
    return response.data;
}

export async function getPdf(businessPartnerId: number) {
    const response = await axios.get(baseURL + "/izlazne-fakture/pdf", {
      responseType: "arraybuffer",
      params: { poslovniPartnerId: businessPartnerId },
    });
    return response.data;
}

export async function getUnits(reasonOfPayment?: string, pageNum?: number) {
    const response = await axios.get(baseURL + "/stavke-izvoda", {
      params: {
        ...(reasonOfPayment !== "" ? { svrhaPlacanja: reasonOfPayment } : {}),
        ...(pageNum ? { pageNum: pageNum } : {}),
      },
    });
    return response.data;
}

export async function getAvailableCredit(partnerId: number) {
    const response = await axios.get(baseURL + "/uplate/kredit", {
      params: { "partnerId": partnerId }
    });
    return response.data;
}

export async function makePayment(payment: any) {
    return await axios.post(baseURL + "/uplate", payment);
}
  
export async function getPayments(invoiceId?: number, unitId?: number, pageNum?: number) {
    const response = await axios.get(baseURL + "/uplate", {
      params: {
        ...(invoiceId ? { fakturaId: invoiceId } : {}),
        ...(unitId ? { stavkaId: unitId } : {}),
        ...(pageNum ? { pageNum: pageNum } : {}),
      },
    });
    return response.data;
}
  