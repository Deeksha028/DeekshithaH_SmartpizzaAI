import { Link } from "react-router-dom";
 
function Navbar() {
  return (
    <nav>
      <Link to="/">Home</Link>
      <Link to="/orders">Orders</Link>
      <Link to="/delivery">Delivery Dashboard</Link>
      <Link to="/track-delivery">Track Delivery</Link>
      <Link to="/delivery-route">Delivery Route</Link>
      <Link to="/recommendations"className="nav-link">AI Recommendations</Link>
    </nav>
  );
}
 
export default Navbar;