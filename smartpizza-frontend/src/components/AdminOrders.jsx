import { useEffect, useState } from "react";
import { getAllOrders, updateOrderStatus } from "../services/orderService";
import { useNavigate } from "react-router-dom";

function AdminOrders() {

  const [orders, setOrders] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    loadOrders();
  }, []);

  const loadOrders = async () => {
    try {
      const res = await getAllOrders();
      setOrders(res.data || []);
    } catch (error) {
      console.log(error);
    }
  };

  const handleStatusChange = async (id, status) => {
    try {
      await updateOrderStatus(id, status);

      // ✅ instant UI update
      setOrders((prevOrders) =>
        prevOrders.map((order) =>
          order.id === id
            ? { ...order, status: status }
            : order
        )
      );

    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div
      style={{
        background: "linear-gradient(135deg, #ffe5b4, #ffb347)",
        minHeight: "100vh",
        padding: "20px"
      }}
    >

      <div style={{ maxWidth: "1000px", margin: "auto" }}>

        {/* HEADER */}
        <div className="d-flex justify-content-between align-items-center mb-4">
          <h2 className="fw-bold">📦 Admin Orders</h2>

          <button
            className="btn btn-outline-dark"
            onClick={() => navigate("/admin")}
          >
            ⬅ Back
          </button>
        </div>

        {/* CARD */}
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
                <th>Status</th>
                <th>Update</th>
              </tr>
            </thead>

            <tbody>

              {orders.length === 0 ? (
                <tr>
                  <td colSpan="5" className="text-center">
                    No orders found 😢
                  </td>
                </tr>
              ) : (
                orders.map((order) => (

                  <tr key={order.id}>

                    <td><strong>{order.id}</strong></td>

                    <td>{order.customerName}</td>

                    <td>₹ {order.totalAmount}</td>

                    {/* ✅ STATUS BADGE */}
                    <td>
                      <span
                        className={`badge ${
                          order.status === "DELIVERED"
                            ? "bg-success"
                            : order.status === "OUT_FOR_DELIVERY"
                            ? "bg-info text-dark"
                            : order.status === "PREPARING"
                            ? "bg-warning text-dark"
                            : "bg-secondary"
                        }`}
                      >
                        {order.status || "DELIVERED"}
                      </span>
                    </td>

                    {/* ✅ DROPDOWN */}
                    <td>
                      <select
                        className="form-select"
                        value={order.status || "DELIVERED"}
                        onChange={(e) =>
                          handleStatusChange(order.id, e.target.value)
                        }
                      >
                        <option value="PENDING">PENDING</option>
                        <option value="PREPARING">PREPARING</option>
                        <option value="OUT_FOR_DELIVERY">OUT_FOR_DELIVERY</option>
                        <option value="DELIVERED">DELIVERED</option>
                      </select>
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

export default AdminOrders;