import { useState } from "react";

function Chatbot() {

  const [messages, setMessages] = useState([
    { text: "👋 Hi! Ask me anything about SmartPizza!", sender: "bot" }
  ]);

  const [input, setInput] = useState("");

  const handleSend = () => {

    if (!input.trim()) return;

    const userMessage = { text: input, sender: "user" };

    let botReply = "Sorry, I didn't understand 😅";

    // ✅ SIMPLE RULE-BASED AI
    const msg = input.toLowerCase();

    if (msg.includes("menu") || msg.includes("pizza")) {
      botReply = "🍕 We have Veg & Non-Veg pizzas. Check the menu page!";
    } 
    else if (msg.includes("cart")) {
      botReply = "🛒 You can view your cart and manage items there.";
    } 
    else if (msg.includes("order")) {
      botReply = "📦 You can track your order in 'My Orders' section.";
    } 
    else if (msg.includes("delivery")) {
      botReply = "🚚 Track delivery from the delivery page!";
    } 
    else if (msg.includes("offers") || msg.includes("coupon")) {
      botReply = "🎟 Use SAVE20 to get discount!";
    } 
    else if (msg.includes("help")) {
      botReply = "Try asking: menu, cart, order, delivery, offers";
    }

    setMessages([
      ...messages,
      userMessage,
      { text: botReply, sender: "bot" }
    ]);

    setInput("");
  };

  return (
    <div
      className="card shadow-lg p-3 mt-4"
      style={{ borderRadius: "15px" }}
    >

      <h5 className="text-center">🤖 SmartPizza Bot</h5>

      {/* Chat */}
      <div
        style={{
          height: "200px",
          overflowY: "auto",
          background: "#f8f9fa",
          padding: "10px",
          borderRadius: "10px"
        }}
      >
        {messages.map((msg, index) => (
          <div
            key={index}
            style={{
              textAlign:
                msg.sender === "user" ? "right" : "left"
            }}
          >
            <p>
              {msg.sender === "bot" ? "🤖" : "👤"} {msg.text}
            </p>
          </div>
        ))}
      </div>

      {/* Input */}
      <div className="d-flex mt-2">
        <input
          className="form-control"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="Ask something..."
        />
        <button
          className="btn ms-2 text-white"
          style={{ backgroundColor: "#c0392b" }}
          onClick={handleSend}
        >
          Send
        </button>
      </div>

    </div>
  );
}

export default Chatbot;