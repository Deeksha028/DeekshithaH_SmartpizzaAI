import { useState } from "react";
import { register } from "../services/authService";
import { useNavigate } from "react-router-dom";

function Register() {

  const navigate = useNavigate();

  const [form, setForm] = useState({
    name: "",
    email: "",
    password: ""
  });

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await register(form);

      localStorage.setItem("name", form.name);

      console.log(response.data);

      alert("✅ Registration Success");

      navigate("/login");

    } catch (error) {
      console.log(error);
      alert("❌ Registration Failed");
    }
  };

  return (
    <div
      className="d-flex justify-content-center align-items-center"
      style={{
        minHeight: "100vh",
        background: "linear-gradient(135deg, #ffe5b4, #ffb347)" // ✅ theme
      }}
    >

      <div
        className="card shadow-lg p-5"
        style={{
          width: "420px",
          borderRadius: "20px"
        }}
      >

        <h2 className="text-center mb-4 fw-bold">
          📝 Register
        </h2>

        <form onSubmit={handleSubmit}>

          {/* Name */}
          <div className="input-group mb-3">
            <span className="input-group-text">👤</span>
            <input
              name="name"
              className="form-control"
              placeholder="Enter Name"
              onChange={handleChange}
              required
            />
          </div>

          {/* Email */}
          <div className="input-group mb-3">
            <span className="input-group-text">📧</span>
            <input
              name="email"
              className="form-control"
              placeholder="Enter Email"
              onChange={handleChange}
              required
            />
          </div>

          {/* Password */}
          <div className="input-group mb-4">
            <span className="input-group-text">🔒</span>
            <input
              name="password"
              type="password"
              className="form-control"
              placeholder="Enter Password"
              onChange={handleChange}
              required
            />
          </div>

          {/* Button */}
          <button
            className="btn w-100 text-white"
            style={{
              backgroundColor: "#c0392b", // 🍕 pepperoni red
              borderRadius: "10px"
            }}
          >
            Register
          </button>

        </form>

        {/* Login Link */}
        <p className="text-center mt-3">
          Already have an account?{" "}
          <span
            style={{ cursor: "pointer", color: "#c0392b" }}
            onClick={() => navigate("/login")}
          >
            Login
          </span>
        </p>

      </div>

    </div>
  );
}

export default Register;