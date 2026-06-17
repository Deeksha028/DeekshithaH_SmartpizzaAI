import { useEffect, useState } from "react";
import { getInvoice } from "../services/paymentService";
import { useNavigate } from "react-router-dom";

function Invoice() {

  const [invoice, setInvoice] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {

    const fetchInvoice = async () => {
      try {
        const paymentId = localStorage.getItem("paymentId");
        const response = await getInvoice(paymentId);

        // ✅ FIX (safe)
        setInvoice(response.data || response);

      } catch (error) {
        console.log(error);
      }
    };

    fetchInvoice();

  }, []);

  if (!invoice) {
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
        <h4>⏳ Loading Invoice...</h4>
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

      <div style={{ maxWidth: "650px", margin: "auto" }}>

        {/* Card */}
        <div
          className="card shadow-lg p-5"
          style={{ borderRadius: "20px" }}
        >

          <h2 className="text-center mb-3 fw-bold">
            🧾 Invoice
          </h2>

          <hr />

          {/* Customer Info */}
          <p>
            <strong>Name:</strong>{" "}
            {localStorage.getItem("customerName")}
          </p>

          <p>
            <strong>Email:</strong>{" "}
            {localStorage.getItem("email")}
          </p>

          <hr />

          {/* Details */}
          <div className="d-flex justify-content-between">
            <p><strong>Payment ID:</strong></p>
            <p>{invoice.paymentId}</p>
          </div>

          <div className="d-flex justify-content-between">
            <p><strong>Order ID:</strong></p>
            <p>{invoice.orderId}</p>
          </div>

          <div className="d-flex justify-content-between">
            <p><strong>Amount:</strong></p>
            <p>₹ {invoice.amount}</p>
          </div>

          <div className="d-flex justify-content-between">
            <p><strong>Tax:</strong></p>
            <p>₹ {invoice.tax}</p>
          </div>

          <hr />

          <div className="d-flex justify-content-between">
            <h5><strong>Final Amount:</strong></h5>
            <h5 className="text-success">
              ₹ {invoice.finalAmount}
            </h5>
          </div>

          <hr />

          <div className="d-flex justify-content-between">
            <p><strong>Payment Method:</strong></p>
            <p>{invoice.paymentMethod}</p>
          </div>

          <div className="d-flex justify-content-between">
            <p><strong>Status:</strong></p>
            <span className="badge bg-success">
              {invoice.paymentStatus}
            </span>
          </div>

          {/* Buttons */}
          <div className="mt-4 d-flex justify-content-between">

            <button
              className="btn text-white"
              style={{ backgroundColor: "#c0392b" }}
              onClick={() => window.print()}
            >
              🖨 Download
            </button>

            <button
              className="btn text-white"
              style={{ backgroundColor: "#c0392b" }}
              onClick={() => navigate("/delivery")}
            >
              🚚 Track Order
            </button>

          </div>

        </div>

      </div>

    </div>
  );
}

export default Invoice;