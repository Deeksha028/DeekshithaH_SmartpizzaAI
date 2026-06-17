import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Checkout() {

  const navigate = useNavigate();
  const [address, setAddress] = useState("");

  const handleCheckout = () => {
    if (!address.trim()) {
      alert("Please enter delivery address");
      return;
    }

    localStorage.setItem("address", address);
    navigate("/payment");
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
          maxWidth: "520px",
          borderRadius: "20px"
        }}
      >

        <h2 className="text-center mb-4 fw-bold">
          📦 Checkout
        </h2>

        {/* Address */}
        <div className="mb-4">
          <label className="form-label fw-bold">
            Delivery Address
          </label>

          <textarea
            className="form-control"
            rows="4"
            placeholder="Enter your full address..."
            value={address}
            onChange={(e) =>
              setAddress(e.target.value)
            }
            style={{ borderRadius: "10px" }}
          />
        </div>

        {/* Buttons */}
        <button
          className="btn w-100 mb-2 text-white"
          style={{
            backgroundColor: "#c0392b", // ✅ pepperoni red theme
            borderRadius: "10px"
          }}
          onClick={handleCheckout}
        >
          Continue To Payment 💳
        </button>

        <button
          className="btn btn-outline-dark w-100"
          onClick={() => navigate("/cart")}
        >
          ⬅ Back to Cart
        </button>

      </div>

    </div>
  );
}

export default Checkout;