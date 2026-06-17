import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { resetPassword } from "../services/authService";

function ResetPassword() {

  const [email, setEmail] = useState("");
  const [otp, setOtp] = useState("");
  const [newPassword, setNewPassword] = useState("");

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {

      const response = await resetPassword(
        email,
        otp,
        newPassword
      );

      alert(response.data);

      navigate("/login");

    } catch (error) {
      console.log(error);
      alert("Invalid OTP or User not found");
    }
  };

  return (
    <div
      className="d-flex justify-content-center align-items-center"
      style={{
        minHeight: "100vh",
        background: "linear-gradient(135deg, #ffe5b4, #ffb347)" // ✅ orange theme
      }}
    >

      <div
        className="card shadow-lg p-5"
        style={{
          width: "430px",
          borderRadius: "20px"
        }}
      >

        <h2 className="text-center mb-4 fw-bold">
          🔑 Reset Password
        </h2>

        <form onSubmit={handleSubmit}>

          {/* Email */}
          <div className="input-group mb-3">
            <span className="input-group-text">📧</span>
            <input
              type="email"
              className="form-control"
              placeholder="Enter Email"
              value={email}
              onChange={(e) =>
                setEmail(e.target.value)
              }
              required
            />
          </div>

          {/* OTP */}
          <div className="input-group mb-3">
            <span className="input-group-text">🔢</span>
            <input
              type="text"
              className="form-control"
              placeholder="Enter OTP"
              value={otp}
              onChange={(e) =>
                setOtp(e.target.value)
              }
              required
            />
          </div>

          {/* Password */}
          <div className="input-group mb-4">
            <span className="input-group-text">🔒</span>
            <input
              type="password"
              className="form-control"
              placeholder="Enter New Password"
              value={newPassword}
              onChange={(e) =>
                setNewPassword(e.target.value)
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
            Reset Password
          </button>

        </form>

        {/* Back Link */}
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

export default ResetPassword;