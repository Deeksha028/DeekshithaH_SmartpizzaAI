import { useEffect, useState } from "react";
import { getOrdersByEmail } from "../services/orderService";
import { useNavigate } from "react-router-dom";

function OrderHistory() {

  const navigate = useNavigate();

  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {

    const fetchOrders = async () => {

      try {

        const email = localStorage.getItem("email");

        const response = await getOrdersByEmail(email);

        setOrders(response.data || []);

      } catch (error) {
        console.log(error);
      } finally {
        setLoading(false);
      }

    };

    fetchOrders();

  }, []);

  // ✅ Loading UI
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
        <h3>⏳ Loading Orders...</h3>
      </div>
    );
  }

  return (
    <div
      style={{
        background: "linear-gradient(135deg, #ffe5b4, #ffb347)", // ✅ same theme
        minHeight: "100vh",
        padding: "20px"
      }}
    >

      <div style={{ maxWidth: "900px", margin: "auto" }}>

        {/* Header */}
        <div className="d-flex justify-content-between align-items-center mb-4">
          <h2 className="fw-bold">📦 My Orders</h2>

          <button
            className="btn btn-outline-dark"
            onClick={() => navigate("/dashboard")}
          >
            ⬅ Back
          </button>
        </div>

        {/* Table Card */}
        <div
          className="card shadow-lg p-3"
          style={{ borderRadius: "15px" }}
        >

          <table className="table table-hover">

            <thead className="table-light">
              <tr>
                <th>ID</th>
                <th>Customer</th>
                <th>Amount</th>
                <th>Delivery Status</th>
              </tr>
            </thead>

            <tbody>

              {orders.length === 0 ? (
                <tr>
                  <td colSpan="4" className="text-center">
                    No Orders Found 😢
                  </td>
                </tr>
              ) : (
                orders.map((order) => (

                  <tr key={order.id}>

                    <td><strong>{order.id}</strong></td>

                    <td>{order.customerName}</td>

                    <td>₹ {order.totalAmount}</td>

                    <td>
                      <span
                        className={`badge ${
                          order.status === "DELIVERED"
                            ? "bg-success"
                            : order.status === "PENDING"
                            ? "bg-warning text-dark"
                            : "bg-secondary"
                        }`}
                      >
                        {order.deliveryStatus}
                      </span>
                    </td>

                  </tr>

                ))
              )}

            </tbody>

          </table>

        </div>

      </div>

    </div>
  );
}

export default OrderHistory;