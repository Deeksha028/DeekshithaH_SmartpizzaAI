import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../services/authService";

function Login() {

  const navigate = useNavigate();

  const [form, setForm] = useState({
    email: "",
    password: ""
  });

  const [loginType, setLoginType] = useState("CUSTOMER");

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {

      const response = await login(form);

      const isAdmin = form.email === "reena@gmail.com";

      if (loginType === "CUSTOMER" && isAdmin) {
        alert("Use Admin Login");
        return;
      }

      if (loginType === "ADMIN" && !isAdmin) {
        alert("Only Admin Allowed");
        return;
      }

      localStorage.setItem("customerName", response.data.name);
      localStorage.setItem("token", response.data.token);
      localStorage.setItem("email", form.email);

      alert("✅ Login Success");

      if (isAdmin) {
        navigate("/admin");
      } else {
        navigate("/dashboard");
      }

    } catch (error) {
      console.log(error);
      alert("❌ Invalid Credentials");
    }
  };

  return (
    <div
      className="d-flex justify-content-center align-items-center vh-100"
      style={{
        background: "linear-gradient(135deg, #ffe5b4, #ffb347)"
      }}
    >

      {/* LOGIN CARD */}
      <div
        className="card shadow-lg p-5"
        style={{
          width: "480px",
          borderRadius: "25px"
        }}
      >

        <h1 className="text-center mb-4 fw-bold">
          🍕 Pizza Login
        </h1>

        {/* TOGGLE BUTTONS */}
        <div className="d-flex mb-4">

          {/* CUSTOMER */}
          <button
            type="button"
            className="btn w-50"
            style={{
              backgroundColor:
                loginType === "CUSTOMER"
                  ? "#c0392b"
                  : "transparent",
              color:
                loginType === "CUSTOMER"
                  ? "white"
                  : "#c0392b",
              border: "1px solid #c0392b"
            }}
            onClick={() => setLoginType("CUSTOMER")}
          >
            Customer
          </button>

          {/* ADMIN */}
          <button
            type="button"
            className="btn w-50 ms-2"
            style={{
              backgroundColor:
                loginType === "ADMIN"
                  ? "#c0392b"
                  : "transparent",
              color:
                loginType === "ADMIN"
                  ? "white"
                  : "#c0392b",
              border: "1px solid #c0392b"
            }}
            onClick={() => setLoginType("ADMIN")}
          >
            Admin
          </button>

        </div>

        {/* FORM */}
        <form onSubmit={handleSubmit}>

          {/* EMAIL */}
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

          {/* PASSWORD */}
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

          {/* LOGIN BUTTON */}
          <button
            type="submit"
            className="btn w-100 text-white"
            style={{
              backgroundColor: "#c0392b",
              borderRadius: "12px"
            }}
          >
            Sign In
          </button>

          {/* FORGOT PASSWORD */}
          <p className="text-center mt-3">
            <a href="/forgot-password">
              Forgot Password?
            </a>
          </p>

          
          <p className="text-center mt-2">
              New user?{" "}
                <span
                  style={{
                    cursor: "pointer",
                    color: "#c0392b",
                    fontWeight: "bold"
            }}
           onClick={() => navigate("/register")}
              >
              Register here
                  </span>
          </p>


        </form>

      </div>

    </div>
  );
}

export default Login;
