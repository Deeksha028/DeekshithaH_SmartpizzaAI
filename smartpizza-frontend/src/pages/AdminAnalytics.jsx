import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import {
  getAnalytics,
  getOrderHeatmap,
  getCustomerTrends,
  getDeliveryPerformance
} from "../services/adminService";

import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer,
  PieChart,
  Pie,
  Cell
} from "recharts";

function AdminAnalytics() {

  const [analytics, setAnalytics] = useState({});
  const [heatmap, setHeatmap] = useState([]);
  const [customers, setCustomers] = useState({});
  const [delivery, setDelivery] = useState({});

  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.clear();
    navigate("/login");
  };

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    try {
      const analyticsRes = await getAnalytics();
      const heatmapRes = await getOrderHeatmap();
      const customerRes = await getCustomerTrends();
      const deliveryRes = await getDeliveryPerformance();

      // ✅ SAFE DATA ASSIGNMENT
      setAnalytics(analyticsRes.data || {});
      setHeatmap(heatmapRes.data || []);
      setCustomers(customerRes.data || {});
      setDelivery(deliveryRes.data || {});

      console.log("Analytics:", analyticsRes.data);
      console.log("Heatmap:", heatmapRes.data);

    } catch (error) {
      console.log(error);
    }
  };

  const deliveryChart = [
    {
      name: "Completed",
      value: delivery.completedDeliveries || 0
    },
    {
      name: "Pending",
      value:
        (delivery.totalDeliveries || 0) -
        (delivery.completedDeliveries || 0)
    }
  ];

  return (
    <div
      style={{
        background: "linear-gradient(135deg, #ffe5b4, #ffb347)", // ✅ FIXED BACKGROUND
        minHeight: "100vh",
        width: "100%",
        padding: "20px"
      }}
    >

      {/* ✅ CENTER CONTENT */}
      <div style={{ maxWidth: "1200px", margin: "auto" }}>

        {/* Header */}
        <div className="d-flex justify-content-between align-items-center mb-4">

  <h2 className="fw-bold">📊 Admin Analytics Dashboard</h2>

  <div>

    {/* ✅ BACK BUTTON */}
    <button
      className="btn me-2"
      onClick={() => navigate("/admin")}
    >
      ⬅ Back
    </button>

    {/* LOGOUT */}
    <button
      className="btn text-white"
      style={{ backgroundColor: "#c0392b" }}
      onClick={handleLogout}
    >
      Logout
    </button>

  </div>

</div>


        {/* KPI CARDS */}
        <div className="row">

          <div className="col-md-3">
            <div className="card p-3 shadow-lg text-center" style={{ borderRadius: "15px" }}>
              <h5>Total Orders</h5>
              <h3>{analytics.totalOrders || 0}</h3>
            </div>
          </div>

          <div className="col-md-3">
            <div className="card p-3 shadow-lg text-center" style={{ borderRadius: "15px" }}>
              <h5>Total Revenue</h5>
              <h3>₹ {analytics.totalRevenue || 0}</h3>
            </div>
          </div>

          <div className="col-md-3">
            <div className="card p-3 shadow-lg text-center" style={{ borderRadius: "15px" }}>
              <h5>Total Customers</h5>
              <h3>{analytics.totalCustomers || 0}</h3>
            </div>
          </div>

          <div className="col-md-3">
            <div className="card p-3 shadow-lg text-center" style={{ borderRadius: "15px" }}>
              <h5>Top Pizza</h5>
              <h3>{analytics.topPizza || "N/A"}</h3>
            </div>
          </div>

        </div>

        <hr />

        {/* HEATMAP */}
        <h4 className="mb-3">📍 Order Heatmap</h4>

        <div className="card p-3 shadow-lg" style={{ borderRadius: "15px" }}>
          {heatmap.length === 0 ? (
            <p>No heatmap data available</p>
          ) : (
            <ResponsiveContainer width="100%" height={300}>
              <BarChart data={heatmap}>
                <XAxis dataKey="location" />
                <YAxis />
                <Tooltip />
                <Bar dataKey="orders" fill="#c0392b" />
              </BarChart>
            </ResponsiveContainer>
          )}
        </div>

        <hr />

        {/* CUSTOMER */}
        <h4 className="mb-3">👥 Customer Trends</h4>

        <div className="card p-3 shadow-lg" style={{ borderRadius: "15px" }}>
          <p>Total Customers : <strong>{customers.totalCustomers || 0}</strong></p>
          <p>Total Orders : <strong>{customers.totalOrders || 0}</strong></p>
          <p>Top Customer : <strong>{customers.topCustomer || "N/A"}</strong></p>
        </div>

        <hr />

        {/* DELIVERY */}
        <h4 className="mb-3">🚚 Delivery Performance</h4>

        <div className="card p-3 shadow-lg" style={{ borderRadius: "15px" }}>
          <ResponsiveContainer width="100%" height={300}>
            <PieChart>
              <Pie data={deliveryChart} dataKey="value" outerRadius={100} label>
                <Cell fill="#2ecc71" />
                <Cell fill="#e74c3c" />
              </Pie>
              <Tooltip />
            </PieChart>
          </ResponsiveContainer>
        </div>

      </div>

    </div>
  );
}

export default AdminAnalytics;
