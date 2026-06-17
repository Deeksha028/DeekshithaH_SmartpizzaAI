import React, { useEffect, useState } from "react";
import { getDeliveryRoute } from "../services/deliveryService";
import { useNavigate } from "react-router-dom";

function DeliveryRoute() {

  const [route, setRoute] = useState(null);
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    getDeliveryRoute(1)
      .then((response) => {
        setRoute(response.data || response);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching route:", error);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return (
      <div
        style={{
          minHeight: "100vh",
          background: "linear-gradient(135deg, #ffe5b4, #ffb347)",
          display: "flex",
          justifyContent: "center",
          alignItems: "center"
        }}
      >
        <h3>⏳ Loading Delivery Route...</h3>
      </div>
    );
  }

  return (
    <div
      style={{
        background: "linear-gradient(135deg, #ffe5b4, #ffb347)", // ✅ theme
        minHeight: "100vh",
        padding: "20px"
      }}
    >

      <div style={{ maxWidth: "700px", margin: "auto" }}>

        {/* Header */}
        <div className="d-flex justify-content-between align-items-center mb-4">
          <h2 className="fw-bold">🚚 Delivery Route</h2>

          <button
            className="btn btn-outline-dark"
            onClick={() => navigate("/delivery")}
          >
            ⬅ Back
          </button>
        </div>

        {/* Route Card */}
        <div
          className="card shadow-lg p-4"
          style={{ borderRadius: "20px" }}
        >

          <h4 className="text-center mb-3 fw-bold">
            📍 Optimized Route
          </h4>

          <hr />

          <p className="text-center">
            <strong>Delivery ID:</strong> {route.deliveryId}
          </p>

          {/* Route highlight */}
          <div
            className="mt-3 p-3 text-center"
            style={{
              background: "#fff3e0",
              borderRadius: "10px",
              fontWeight: "bold",
              fontSize: "18px"
            }}
          >
            🚚 {route.recommendedRoute}
          </div>

          {/* Extra info section (optional but nice) */}
          <div className="mt-4 text-center">
            <p>
              ⚡ Fastest path selected for quicker delivery
            </p>
          </div>

        </div>

      </div>

    </div>
  );
}

export default DeliveryRoute;
