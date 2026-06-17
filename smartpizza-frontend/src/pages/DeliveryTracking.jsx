import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { getDeliveryByOrderId } from "../services/deliveryService";

function DeliveryTracking() {

  const navigate = useNavigate();

  const [delivery, setDelivery] = useState(null);
  const [loading, setLoading] = useState(true);

  const handleLogout = () => {
    localStorage.clear();
    navigate("/login");
  };

  useEffect(() => {
    const orderId = localStorage.getItem("orderId");

    getDeliveryByOrderId(orderId)
      .then((data) => {
        setDelivery(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error:", error);
        setLoading(false);
      });

  }, []);

  return (
    <div
      style={{
        background: "linear-gradient(135deg, #ffe5b4, #ffb347)", // ✅ same theme
        minHeight: "100vh",
        padding: "20px"
      }}
    >

      {/* Center Container */}
      <div style={{ maxWidth: "600px", margin: "auto" }}>

        {/* Header */}
        <div className="d-flex justify-content-between align-items-center mb-3">
          <h2 className="fw-bold">🚚 Order Status</h2>

          <button
            className="btn text-white"
            style={{ backgroundColor: "#c0392b" }} // pepperoni red
            onClick={handleLogout}
          >
            Logout
          </button>
        </div>

        {/* CONTENT */}
        {loading ? (

          <div className="card p-4 text-center shadow-lg">
            <h4>⏳ Loading...</h4>
          </div>

        ) : delivery ? (

          <div
            className="card p-4 mt-3 shadow-lg"
            style={{ borderRadius: "20px" }}
          >

            {/* Status */}
            <h4
              className="text-center"
              style={{
                color:
                  delivery.status === "DELIVERED"
                    ? "green"
                    : "#c0392b"
              }}
            >
              {delivery.status}
            </h4>

            <hr />

            <p>
              <strong>Order ID:</strong> {delivery.orderId}
            </p>

            <p>
              <strong>Delivery Person:</strong>{" "}
              {delivery.deliveryPerson}
            </p>

            <p>
              <strong>Current Location:</strong>{" "}
              {delivery.currentLocation}
            </p>

            <p>
              <strong>ETA:</strong>{" "}
              {delivery.etaMinutes} mins
            </p>

            {/* ✅ Simple Progress Bar */}
            <div className="progress mt-3">
  <div
    className="progress-bar"
    style={{
      width:
        delivery.status === "PENDING"
          ? "25%"
          : delivery.status === "PREPARING"
          ? "50%"
          : delivery.status === "OUT_FOR_DELIVERY"
          ? "75%"
          : "100%",
      backgroundColor: "#c0392b"
    }}
  >
    {delivery.status}
  </div>
</div>


          </div>

        ) : (

          <div className="card p-4 text-center shadow-lg text-danger">
            Delivery information not found.
          </div>

        )}

        {/* Buttons */}
        <div className="mt-3 text-center">

          <button
            className="btn btn-outline-dark me-2"
            onClick={() => navigate("/orders")}
          >
            My Orders
          </button>

          <button
            className="btn text-white"
            style={{ backgroundColor: "#c0392b" }}
            onClick={() => navigate("/delivery-route")}
          >
            View Route
          </button>

        </div>

      </div>

    </div>
  );
}

export default DeliveryTracking;