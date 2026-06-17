import { useEffect, useState } from "react";
import { getAllPizzas } from "../services/pizzaService";
import { addToCart } from "../services/cartService";
import { useNavigate } from "react-router-dom";

function PizzaList() {

  const navigate = useNavigate();

  const [pizzas, setPizzas] = useState([]);
  const [filter, setFilter] = useState("ALL");

  useEffect(() => {
    loadPizzas();
  }, []);

  const loadPizzas = async () => {
    try {
      const response = await getAllPizzas();
      setPizzas(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  const handleLogout = () => {
    localStorage.clear();
    navigate("/login");
  };

  const handleAddToCart = async (pizzaId) => {
    try {
      const token = localStorage.getItem("token");

      const cartData = {
        pizzaId: pizzaId,
        quantity: 1,
      };

      await addToCart(cartData, token);
      alert("✅ Pizza added");
    } catch (error) {
      console.log(error);
      alert("❌ Failed");
    }
  };

  // ✅ FILTER LOGIC
  const filteredPizzas = pizzas.filter((pizza) => {
    if (filter === "ALL") return true;
    return pizza.category.toLowerCase() === filter;
  });

  return (
    <div
      style={{
        background: "linear-gradient(135deg, #ffe5b4, #ffb347)",
        minHeight: "100vh",
        padding: "20px"
      }}
    >

      {/* HEADER */}
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h2 className="fw-bold">🍕 Pizza Menu</h2>
        <div>

        {/* BACK BUTTON */}
          <button
            className="btn btn-outline-dark me-2"
              onClick={() => navigate("/dashboard")}>
                ⬅ Back
                </button>

          <button
            className="btn btn-light me-2"
            onClick={() => navigate("/cart")}
          >
            🛒 Cart
          </button>

          <button
            className="btn btn-danger"
            onClick={handleLogout}
          >
            Logout
          </button>
        </div>
      </div>

      {/* FILTER BUTTONS */}
      <div className="d-flex mb-4">

        <button
          className="btn me-2"
          style={{
            backgroundColor: filter === "ALL" ? "#c0392b" : "transparent",
            color: filter === "ALL" ? "white" : "#c0392b",
            border: "1px solid #c0392b"
          }}
          onClick={() => setFilter("ALL")}
        >
          All
        </button>

        <button
          className="btn me-2"
          style={{
            backgroundColor: filter === "veg" ? "#c0392b" : "transparent",
            color: filter === "veg" ? "white" : "#c0392b",
            border: "1px solid #c0392b"
          }}
          onClick={() => setFilter("veg")}
        >
          Veg
        </button>

        <button
          className="btn"
          style={{
            backgroundColor: filter === "non-veg" ? "#c0392b" : "transparent",
            color: filter === "non-veg" ? "white" : "#c0392b",
            border: "1px solid #c0392b"
          }}
          onClick={() => setFilter("non-veg")}
        >
          Non-Veg
        </button>

      </div>

      {/* PIZZA CARDS */}
      <div className="row">
        {filteredPizzas.map((pizza) => (
          <div className="col-md-4 mb-4" key={pizza.id}>

            <div
              className="card shadow-lg border-0 p-3 text-center"
              style={{
                borderRadius: "20px",
                transition: "0.3s"
              }}
            >

              <h4 className="fw-bold mt-2">
                🍕 {pizza.name}
              </h4>

              <p>{pizza.category}</p>
              <p>Size: {pizza.size}</p>

              <h5 className="text-success">
                ₹{pizza.price}
              </h5>

              <button
                className="btn mt-2 text-white"
                style={{
                  backgroundColor: "#c0392b",
                  borderRadius: "10px"
                }}
                onClick={() => handleAddToCart(pizza.id)}
              >
                Add To Cart
              </button>

            </div>

          </div>
        ))}
      </div>

    </div>
  );
}

export default PizzaList;