import React, { useState } from "react";
import { getDeliveryByOrderId } from "../services/deliveryService";

function CustomerDeliveryPage() {

  const [orderId, setOrderId] = useState("");
  const [delivery, setDelivery] = useState(null);

  const handleSearch = async () => {
    try {
      const data = await getDeliveryByOrderId(orderId);
      setDelivery(data);
    } catch (error) {
      console.error(error);
      alert("Delivery not found");
    }
  };

  return (
    <div
      style={{
        background: "linear-gradient(135deg, #ffe5b4, #ffb347)", // ✅ same theme
        minHeight: "100vh",
        padding: "20px"
      }}
    >

      {/* Search Card */}
      <div
        className="card shadow-lg p-4 mx-auto"
        style={{ maxWidth: "500px", borderRadius: "20px" }}
      >

        <h3 className="text-center mb-3 fw-bold">
          🚚 Track Your Order
        </h3>

        <div className="input-group mb-3">
          <input
            type="number"
            className="form-control"
            placeholder="Enter Order ID"
            value={orderId}
            onChange={(e) => setOrderId(e.target.value)}
            style={{ borderRadius: "10px" }}
          />

          <button
            className="btn text-white"
            style={{ backgroundColor: "#c0392b" }} // 🍕 pepperoni red
            onClick={handleSearch}
          >
            Track
          </button>
        </div>

      </div>

      {/* Delivery Details Card */}
      {delivery && (
        <div
          className="card shadow-lg p-4 mx-auto mt-4"
          style={{ maxWidth: "500px", borderRadius: "20px" }}
        >

          <h4 className="text-center mb-3 fw-bold">
            📦 Delivery Details
          </h4>

          <div className="d-flex justify-content-between">
            <p><strong>Order ID:</strong></p>
            <p>{delivery.orderId}</p>
          </div>

          <div className="d-flex justify-content-between">
            <p><strong>Delivery Person:</strong></p>
            <p>{delivery.deliveryPerson}</p>
          </div>

          <div className="d-flex justify-content-between">
            <p><strong>Location:</strong></p>
            <p>{delivery.currentLocation}</p>
          </div>

          <div className="d-flex justify-content-between">
            <p><strong>ETA:</strong></p>
            <p>{delivery.etaMinutes} mins</p>
          </div>

          <div className="d-flex justify-content-between">
            <p><strong>Status:</strong></p>
            <span className="badge bg-success">
              {delivery.status}
            </span>
          </div>

        </div>
      )}

    </div>
  );
}

export default CustomerDeliveryPage;