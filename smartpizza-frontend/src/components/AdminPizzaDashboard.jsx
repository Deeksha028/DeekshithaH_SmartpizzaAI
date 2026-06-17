
import { useEffect, useState } from "react";
import { getAllPizzas, addPizza, deletePizza, updatePizza} 
from "../services/pizzaService";
import { useNavigate } from "react-router-dom";

function AdminPizzaDashboard() {

  const navigate = useNavigate();

  const [pizzas, setPizzas] = useState([]);


  const [form, setForm] = useState({
    name: "",
    price: "",
    size: "",
    category: ""
  });

  useEffect(() => {
    loadPizzas();
  }, []);

  const loadPizzas = async () => {
    try {
      const response = await getAllPizzas();
      setPizzas(response.data || []);
    } catch (error) {
      console.log(error);
    }
  };

  const handleLogout = () => {
    localStorage.clear();
    navigate("/login");
  };

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };
  const [editingId, setEditingId] = useState(null);

  const handleAddPizza = async (e) => {
  e.preventDefault();

  try {
    if (editingId) {
      await updatePizza(editingId, form);
      alert("✅ Pizza Updated");
      setEditingId(null);
    } else {
      await addPizza(form);
      alert("✅ Pizza Added");
    }

    setForm({
      name: "",
      price: "",
      size: "",
      category: ""
    });

    loadPizzas();

  } catch (error) {
    console.log(error);
  }
};

  const handleDelete = async (id) => {
    try {
      await deletePizza(id);
      alert("🗑️ Pizza Deleted");
      loadPizzas();
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

      <div style={{ maxWidth: "900px", margin: "auto" }}>

        {/* Header */}
        <div className="d-flex justify-content-between mb-4">
  <h2>🍕 Admin Pizza Dashboard</h2>

  <div>
    {/* ✅ NEW BUTTON */}

     <button
              className="btn me-2 text-white"
              style={{ backgroundColor: "#c0392b" }}
              onClick={() => navigate("/admin-orders")}
            >
              📦 Manage Orders
      </button>
    <button
      className="btn me-2 text-white"
      style={{ backgroundColor: "#c0392b" }}
      onClick={() => navigate("/admin-analytics")}
    >
      📊 Analytics
    </button>

    <button
      className="btn text-white"
      style={{ backgroundColor: "#c0392b" }}
      onClick={handleLogout}
    >
      Logout
    </button>
  </div>
</div>

        {/* ADD PIZZA FORM */}
        <div className="card p-4 shadow-lg mb-4"
             style={{ borderRadius: "15px" }}>

          <h4>Add New Pizza</h4>

          <form onSubmit={handleAddPizza}>

            <input
              name="name"
              className="form-control mb-2"
              placeholder="Pizza Name"
              value={form.name}
              onChange={handleChange}
              required
            />

            <input
              name="price"
              type="number"
              className="form-control mb-2"
              placeholder="Price"
              value={form.price}
              onChange={handleChange}
              required
            />

            <input
              name="size"
              className="form-control mb-2"
              placeholder="Size (Small/Medium/Large)"
              value={form.size}
              onChange={handleChange}
              required
            />

            <select
              name="category"
              className="form-control mb-3"
              value={form.category}
              onChange={handleChange}
              required
            >
              <option value="">Select Category</option>
              <option value="Veg">Veg</option>
              <option value="Non-Veg">Non-Veg</option>
            </select>

            <button
              className="btn text-white w-100"
              style={{ backgroundColor: "#c0392b" }}
            >
              Add Pizza
            </button>


          </form>

        </div>

        {/* PIZZA LIST */}
        <div className="card p-3 shadow-lg"
             style={{ borderRadius: "15px" }}>

          <h4>All Pizzas</h4>

          <table className="table">

            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Size</th>
                <th>Category</th>
                <th>Action</th>
              </tr>
            </thead>

            <tbody>

              {pizzas.length === 0 ? (
                <tr>
                  <td colSpan="6" className="text-center">
                    No pizzas available
                  </td>
                </tr>
              ) : (
                pizzas.map((pizza) => (
                  <tr key={pizza.id}>
                    <td>{pizza.id}</td>
                    <td>{pizza.name}</td>
                    <td>₹ {pizza.price}</td>
                    <td>{pizza.size}</td>
                    <td>{pizza.category}</td>

                    <td>
                      <button
                        className="btn btn-danger btn-sm"
                        onClick={() => handleDelete(pizza.id)}
                      >
                        Delete
                      </button>

                      <button
                        className="btn btn-warning btn-sm me-2"
                        onClick={() => {
                        setForm(pizza);
                        setEditingId(pizza.id);
                          }}
                          >
                            Edit
                    </button>
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

export default AdminPizzaDashboard;
