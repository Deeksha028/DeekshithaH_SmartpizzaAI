import React, { useEffect, useState } from "react";
import { getRecommendations } from "../services/recommendationService";

function Recommendation() {

  const [recommendations, setRecommendations] = useState([]);

  useEffect(() => {
    loadRecommendations();
  }, []);

  const loadRecommendations = async () => {
    try {
      const response = await getRecommendations(1);
      setRecommendations(response.data || []);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div
      style={{
        background: "linear-gradient(135deg, #f6d365, #fda085)",
        minHeight: "100vh",
        padding: "20px"
      }}
    >

      {/* Header */}
      <h2 className="text-center text-white mb-4 fw-bold">
        🤖 AI Recommended Pizzas For You
      </h2>

      {/* No data */}
      {recommendations.length === 0 && (
        <h4 className="text-center text-white">
          No recommendations available 😢
        </h4>
      )}

      {/* Cards */}
      <div className="row">
        {recommendations.map((item, index) => (

          <div className="col-md-4 mb-4" key={index}>

            <div
              className="card shadow-lg border-0 h-100 p-3"
              style={{ borderRadius: "20px" }}
            >

              <div className="card-body text-center">

                {/* Pizza Title */}
                <h4 className="fw-bold">
                  🍕 {item.pizza}
                </h4>

                <hr />

                {/* Combo */}
                <p>
                  <strong>🍽 Combo:</strong>
                  <br />
                  {item.combo}
                </p>

                {/* Reason */}
                <p>
                  <strong>💡 Why this?</strong>
                  <br />
                  {item.reason}
                </p>

              </div>

            </div>

          </div>

        ))}
      </div>

    </div>
  );
}

export default Recommendation;