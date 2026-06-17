import {
  BrowserRouter,
  Routes,
  Route,
  Navigate
} from "react-router-dom";

import Register from "./pages/Register";
import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";
import PizzaList from "./pages/PizzaList";
import Checkout from "./pages/Checkout";
import Payment from "./pages/Payment";
import DeliveryTracking from "./pages/DeliveryTracking";
import Cart from "./pages/Cart";
import OrderHistory from "./pages/OrderHistory";
import CustomerDeliveryPage from "./pages/CustomDeliveryPage";
import DeliveryRoute from "./routes/DeliveryRoute";
import AdminAnalytics from "./pages/AdminAnalytics";
import Recommendation from "./pages/Recommendation";
import Invoice from "./pages/Invoice";
import ForgotPassword from "./pages/ForgetPassword";
import ResetPassword from "./pages/ResetPassword";
import AdminPizzaDashboard from "./components/AdminPizzaDashboard";
import AdminOrders from "./components/AdminOrders";

function App() {

  return (

    <BrowserRouter>

      <Routes>

        {/* ✅ FIXED ROUTES */}
        <Route path="/admin" element={<AdminPizzaDashboard />} />
        <Route path="/admin-analytics" element={<AdminAnalytics />} />
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/pizzas" element={<PizzaList />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/checkout" element={<Checkout />} />
        <Route path="/payment" element={<Payment />} />
        <Route path="/delivery" element={<DeliveryTracking />} />
        <Route path="/orders" element={<OrderHistory />} />
        <Route path="/track-delivery" element={<CustomerDeliveryPage />} />
        <Route path="/delivery-route" element={<DeliveryRoute />} />
        <Route path="/recommendations" element={<Recommendation />} />
        <Route path="/invoice" element={<Invoice />} />
        <Route path="/forgot-password" element={<ForgotPassword />} />
        <Route path="/reset-password" element={<ResetPassword />} />
        <Route path="/admin-orders" element={<AdminOrders />} />

      </Routes>

    </BrowserRouter>
  );
}

export default App;