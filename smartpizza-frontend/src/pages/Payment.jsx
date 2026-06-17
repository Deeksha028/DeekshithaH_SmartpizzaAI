import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { clearCart } from "../services/cartService";
import { placeOrder } from "../services/orderService";
import { makePayment } from "../services/paymentService";
import { getCouponByCode } from "../services/couponService";

function Payment() {

  const navigate = useNavigate();

  const totalAmount =
    Number(localStorage.getItem("grandTotal")) || 0;

  const [couponCode, setCouponCode] = useState("");
  const [discount, setDiscount] = useState(0);
  const [finalAmount, setFinalAmount] = useState(totalAmount);

  const [paymentMethod, setPaymentMethod] = useState("UPI");

  const applyCoupon = async () => {
    try {
      const coupon = await getCouponByCode(couponCode);

      if (coupon.active) {
        const discountAmount =
          (totalAmount * coupon.discount) / 100;

        setDiscount(discountAmount);
        setFinalAmount(totalAmount - discountAmount);

        alert("Coupon Applied Successfully");
      }
    } catch (error) {
      alert("Invalid Coupon");
    }
  };

  const handlePayment = async () => {
    try {

      const email = localStorage.getItem("email");
      const customerName = localStorage.getItem("customerName");

      const orderData = {
        customerName: customerName,
        customerEmail: email,
        totalAmount: finalAmount
      };

      const orderResponse = await placeOrder(orderData);
      const orderId = orderResponse.data.id;

      localStorage.setItem("orderId", orderId);

      const paymentData = {
        orderId: orderId,
        amount: finalAmount,
        paymentMethod: paymentMethod,
        paymentGateway: "RAZORPAY"
      };

      const paymentResponse = await makePayment(paymentData);

      localStorage.setItem("paymentId", paymentResponse.id);

      await clearCart(email);
      localStorage.removeItem("cart");

      alert("✅ Payment Successful");

      navigate("/invoice");

    } catch (error) {
      console.log(error);
      alert("❌ Payment Failed");
    }
  };

  return (
    <div
      style={{
        // ✅ SAME AS LOGIN / CART
        background: "linear-gradient(135deg, #ffe5b4, #ffb347)",
        minHeight: "100vh",
        padding: "20px"
      }}
    >

      {/* Card */}
      <div
        className="card shadow-lg p-5 mx-auto"
        style={{
          maxWidth: "480px",
          borderRadius: "20px"
        }}
      >

        <h2 className="text-center mb-4 fw-bold">
          💳 Payment
        </h2>

        {/* Order Summary */}
        <div
          className="p-3 mb-3"
          style={{
            background: "#fff3e0",
            borderRadius: "10px"
          }}
        >
          <p><strong>Name:</strong> {localStorage.getItem("customerName")}</p>
          <p><strong>Email:</strong> {localStorage.getItem("email")}</p>

          <h5 className="text-dark">
            Total: ₹ {totalAmount}
          </h5>
        </div>

        {/* Payment Method */}
        <div className="mb-3">
          <label className="form-label fw-bold">
            Select Payment Method
          </label>

          <select
            className="form-control"
            value={paymentMethod}
            onChange={(e) =>
              setPaymentMethod(e.target.value)
            }
            style={{ borderRadius: "10px" }}
          >
            <option value="UPI">UPI</option>
            <option value="CARD">CARD</option>
            <option value="NETBANKING">NET BANKING</option>
            <option value="COD">CASH ON DELIVERY</option>
          </select>
        </div>

        {/* Coupon Section */}
        <div
          className="card p-3 mb-3"
          style={{ borderRadius: "15px" }}
        >

          <h5>Total Amount : ₹{totalAmount}</h5>

          <h5>Discount : ₹{discount}</h5>

          <h4 className="text-success">
            Final Amount : ₹{finalAmount}
          </h4>

          <input
            type="text"
            className="form-control mt-3"
            placeholder="Enter Coupon Code"
            value={couponCode}
            onChange={(e) =>
              setCouponCode(e.target.value)
            }
            style={{ borderRadius: "10px" }}
          />

          <button
            className="btn mt-2 text-white"
            style={{
              backgroundColor: "#c0392b",
              borderRadius: "10px"
            }}
            onClick={applyCoupon}
          >
            Apply Coupon
          </button>

        </div>

        {/* Pay Button */}
        <button
          className="btn w-100 text-white"
          style={{
            backgroundColor: "#c0392b",
            borderRadius: "10px"
          }}
          onClick={handlePayment}
        >
          Pay Now 🚀
        </button>

        {/* Back */}
        <button
          className="btn btn-outline-dark w-100 mt-2"
          onClick={() => navigate("/checkout")}
        >
          ⬅ Back
        </button>

      </div>

    </div>
  );
}

export default Payment;