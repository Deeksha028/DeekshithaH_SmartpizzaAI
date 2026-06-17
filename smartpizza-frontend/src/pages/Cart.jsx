import { useEffect, useState } from "react";
import { getCart, removeCartItem } from "../services/cartService";
import { useNavigate } from "react-router-dom";
import Recommendation from "./Recommendation";

function Cart() {

  const navigate = useNavigate();
  const [items, setItems] = useState([]);

  const email = localStorage.getItem("email");

  useEffect(() => {
    loadCart();
  }, []);

  const loadCart = async () => {
    try {
      const response = await getCart(email);
      setItems(response.data || []);
    } catch (error) {
      console.log(error);
    }
  };

  const handleRemove = async (id) => {
    try {
      await removeCartItem(id);
      alert("🗑️ Item removed");
      loadCart();
    } catch (error) {
      console.log(error);
    }
  };

  // ✅ Increase quantity
  const increaseQty = (id) => {
    setItems((prev) =>
      prev.map((item) =>
        item.id === id
          ? {
              ...item,
              quantity: item.quantity + 1,
              totalPrice:
                (item.totalPrice / item.quantity) *
                (item.quantity + 1)
            }
          : item
      )
    );
  };

  // ✅ Decrease quantity
  const decreaseQty = (id) => {
    setItems((prev) =>
      prev.map((item) =>
        item.id === id && item.quantity > 1
          ? {
              ...item,
              quantity: item.quantity - 1,
              totalPrice:
                (item.totalPrice / item.quantity) *
                (item.quantity - 1)
            }
          : item
      )
    );
  };

  const total = items.reduce(
    (sum, item) => sum + item.totalPrice,
    0
  );

  return (
    <div
      style={{
        // ✅ SAME AS LOGIN BACKGROUND
        background: "linear-gradient(135deg, #ffe5b4, #ffb347)",
        minHeight: "100vh",
        padding: "20px"
      }}
    >

      {/* Header */}
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h2 className="fw-bold text-dark">🛒 My Cart</h2>

        <button
          className="btn btn-light"
          onClick={() => navigate("/pizzas")}
        >
          ⬅ Back
        </button>
      </div>

      {/* Cart Items */}
      <div className="row">
        {items.length === 0 ? (
          <h4 className="text-dark">Cart is empty 😢</h4>
        ) : (
          items.map((item) => (
            <div className="col-md-4 mb-4" key={item.id}>

              <div
                className="card shadow-lg border-0 h-100"
                style={{ borderRadius: "20px" }}
              >

                <div className="card-body text-center">

                  <h4>🍕 {item.pizzaName}</h4>

                  {/* ✅ Quantity Buttons */}
                  <div className="d-flex justify-content-center align-items-center mt-2">

                    <button
                      className="btn btn-danger btn-sm me-2"
                      onClick={() => decreaseQty(item.id)}
                    >
                      -
                    </button>

                    <span style={{ fontWeight: "bold" }}>
                      {item.quantity}
                    </span>

                    <button
                      className="btn btn-success btn-sm ms-2"
                      onClick={() => increaseQty(item.id)}
                    >
                      +
                    </button>

                  </div>

                  <h5 className="text-success mt-3">
                    ₹ {item.totalPrice}
                  </h5>

                  <button
                    className="btn btn-danger mt-3 w-100"
                    onClick={() =>
                      handleRemove(item.id)
                    }
                  >
                    Remove
                  </button>

                </div>

              </div>

            </div>
          ))
        )}
      </div>

      {/* Total Section */}
      {items.length > 0 && (
        <div
          className="card mt-4 p-3 shadow-lg"
          style={{ borderRadius: "15px" }}
        >
          <h4 className="text-center">
            Grand Total: ₹ {total}
          </h4>

          <button
            className="btn btn-success mt-3 w-100"
            onClick={() => {
              localStorage.setItem("grandTotal", total);
              navigate("/checkout");
            }}
          >
            Proceed To Checkout
          </button>
        </div>
      )}

      {/* Recommendation */}
      <div className="mt-5">
        <Recommendation />
      </div>

    </div>
  );
}

export default Cart;