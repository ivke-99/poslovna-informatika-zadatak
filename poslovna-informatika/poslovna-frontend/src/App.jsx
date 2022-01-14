import './App.css';
import InvoiceSection from './components/InvoiceSection';
import { AppBar, Tab, Tabs } from '@mui/material';
import UnitSection from './components/UnitSection';
import PaymentSection from './components/PaymentsSection';
import DownloadPdf from './components/DownloadPdf';
import DailyBalanceSection from './components/DailyBalanceSection';
import { Route, BrowserRouter, Switch, Link } from "react-router-dom";
import { ThemeProvider, createTheme } from '@mui/material/styles';

function App() {
  const routes = ["/daily-balance", "/ap-pdf", "/invoice", "/payment", "/unit"]

  const darkTheme = createTheme({
    palette: {
      mode: 'light'
    },
  });

  return (
    <div className="app">
      <ThemeProvider theme={darkTheme}>
      <BrowserRouter>
        <Route
          path="/"
          render={(history) => (
            <AppBar color="secondary">
              <Tabs
                value={
                  history.location.pathname !== "/"
                    ? history.location.pathname
                    : false
                }
              >
                <Tab
                  value={routes[0]}
                  label="Daily Balance"
                  component={Link}
                  to={routes[0]}
                />
                <Tab
                  value={routes[1]}
                  label="AP Ledger PDF"
                  component={Link}
                  to={routes[1]}
                />
                <Tab
                  value={routes[2]}
                  label="Invoices"
                  component={Link}
                  to={routes[2]}
                />
                <Tab
                  value={routes[3]}
                  label="Payments"
                  component={Link}
                  to={routes[3]}
                />
                <Tab
                  value={routes[4]}
                  label="Payment units"
                  component={Link}
                  to={routes[4]}
                />
              </Tabs>
            </AppBar>
          )}
        />

        <Switch>
          <Route path="/daily-balance" component={DailyBalanceSection} />
          <Route path="/ap-pdf" component={DownloadPdf} />
          <Route path="/invoice" component={InvoiceSection} />
          <Route path="/payment" component={PaymentSection} />
          <Route path="/unit" component={UnitSection} />
        </Switch>
      </BrowserRouter>
      </ThemeProvider>
    </div>
  );
}

export default App;
