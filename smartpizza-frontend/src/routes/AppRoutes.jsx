import { BrowserRouter, Routes, Route } from "react-router-dom";
 
import Login from "../pages/Login";
import Register from "../pages/Register";
import Menu from "../pages/Menu";
import Cart from "../pages/Cart";
import Checkout from "../pages/Checkout";
import Payment from "../pages/Payment";
import DeliveryTracking from "../pages/DeliveryTracking";
import Dashboard from "../pages/Dashboard";
import PizzaList from "../pages/PizzaList";
import Invoice from "../pages/Invoice";
import ForgotPassword from "../pages/ForgetPassword";
import ResetPassword from "../pages/ResetPassword";
 
function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/pizzas" element={<PizzaList/>} />
        <Route path="/menu" element={<Menu />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/checkout" element={<Checkout />} />
        <Route path="/payment" element={<Payment />} />
        <Route path="/delivery" element={<DeliveryTracking />} />
        <Route path="/admin" element={<Dashboard />} />
        <Route path ="/invoice" element={<Invoice/>}/>
        <Route path="/forgot-password" element={<ForgotPassword/>}/>
        <Route path="/reset-password" element={<ResetPassword/>}/>
      </Routes>
    </BrowserRouter>
  );
}
 
export default AppRoutes;