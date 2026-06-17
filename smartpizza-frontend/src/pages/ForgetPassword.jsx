import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { forgotPassword } from "../services/authService";

function ForgotPassword() {

  const [email, setEmail] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {

    e.preventDefault();

    try {

      const response = await forgotPassword(email);

      alert(response.data);

      localStorage.setItem("resetEmail", email);

      navigate("/reset-password");

    } catch (error) {

      console.log(error);
      alert("Email not found");

    }
  };

  return (
    <div
      className="d-flex justify-content-center align-items-center"
      style={{
        minHeight: "100vh",
        background: "linear-gradient(135deg, #ffe5b4, #ffb347)" // ✅ same theme
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
          🔐 Forgot Password
        </h2>

        <form onSubmit={handleSubmit}>

          {/* Email Input */}
          <div className="input-group mb-3">
            <span className="input-group-text">📧</span>

            <input
              type="email"
              className="form-control"
              placeholder="Enter your email"
              value={email}
              onChange={(e) =>
                setEmail(e.target.value)
              }
              required
            />
          </div>

          {/* Button */}
          <button
            type="submit"
            className="btn w-100 text-white"
            style={{
              backgroundColor: "#c0392b", // 🍕 pepperoni red
              borderRadius: "10px"
            }}
          >
            Send OTP
          </button>

        </form>

        {/* Back to Login */}
        <p className="text-center mt-3">
          <span
            style={{ cursor: "pointer", color: "#c0392b" }}
            onClick={() => navigate("/login")}
          >
            ⬅ Back to Login
          </span>
        </p>

      </div>

    </div>
  );
}

export default ForgotPassword;
