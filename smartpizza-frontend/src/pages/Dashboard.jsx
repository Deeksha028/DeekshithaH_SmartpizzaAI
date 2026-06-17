import { useNavigate } from "react-router-dom";
import Chatbot from "../components/Chatbot";

function Dashboard() {

  const navigate = useNavigate();

  const name = localStorage.getItem("customerName");

  const handleLogout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (
    <div
      style={{
        background: "linear-gradient(135deg, #ffe5b4, #ffb347)",
        minHeight: "100vh",
        padding: "20px"
      }}
    >

      <div style={{ maxWidth: "800px", margin: "auto" }}>

        {/* Header */}
        <div className="d-flex justify-content-between mb-4">
          <h2>👋 Welcome {name}</h2>

          <button
            className="btn text-white"
            style={{ backgroundColor: "#c0392b" }}
            onClick={handleLogout}
          >
            Logout
          </button>
        </div>

        {/* Dashboard Cards */}
        <div className="row">

          {/* MENU */}
          <div className="col-md-4 mb-4">
            <div
              className="card p-4 text-center shadow-lg"
              onClick={() => navigate("/pizzas")}
              style={{ cursor: "pointer", borderRadius: "15px" }}
            >
              🍕
              <h5 className="mt-2">Menu</h5>
            </div>
          </div>

          {/* CART */}
          <div className="col-md-4 mb-4">
            <div
              className="card p-4 text-center shadow-lg"
              onClick={() => navigate("/cart")}
              style={{ cursor: "pointer", borderRadius: "15px" }}
            >
              🛒
              <h5 className="mt-2">Cart</h5>
            </div>
          </div>

          {/* ORDERS */}
          <div className="col-md-4 mb-4">
            <div
              className="card p-4 text-center shadow-lg"
              onClick={() => navigate("/orders")}
              style={{ cursor: "pointer", borderRadius: "15px" }}
            >
              📦
              <h5 className="mt-2">My Orders</h5>
            </div>
          </div>

          {/* DELIVERY
          <div className="col-md-4 mb-4">
            <div
              className="card p-4 text-center shadow-lg"
              onClick={() => navigate("/delivery")}
              style={{ cursor: "pointer", borderRadius: "15px" }}
            >
              🚚
              <h5 className="mt-2">Track Delivery</h5>
            </div>
          </div> */}

          {/* RECOMMENDATION
          <div className="col-md-4 mb-4">
            <div
              className="card p-4 text-center shadow-lg"
              onClick={() => navigate("/recommendations")}
              style={{ cursor: "pointer", borderRadius: "15px" }}
            >
              🤖
              <h5 className="mt-2">Recommendations</h5>
            </div>
          </div> */}
          <Chatbot />
        </div>

      </div>

    </div>
  );
}

export default Dashboard;