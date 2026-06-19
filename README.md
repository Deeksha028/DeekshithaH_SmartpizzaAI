SmartPizza AI – Intelligent Food Ordering & Delivery System
📌 Project Description
SmartPizza AI is a full-stack web application designed to automate and optimize the pizza ordering and delivery process. The system allows customers to order pizzas, receive AI-based recommendations, apply coupons, make secure payments, and track deliveries in real time.
The system also provides administrative dashboards for managing orders, analytics, and business insights.

🚀 Features
👤 Customer Features

User Registration & Login (JWT Authentication)
Browse Pizza Menu
Search & Filter Pizzas
Add to Cart
Apply Coupons
Place Orders
Online Payment (Razorpay / Stripe)
Track Delivery in Real-Time
View Order History
AI-based Pizza Recommendations
Invoice Generation


🛠 Admin Features

Manage Pizzas (Add / Edit / Delete)
Manage Orders
Manage Coupons
View Revenue Analytics
Order Heatmaps
Customer Trends
Delivery Performance Dashboard


🚚 Delivery Features

Login
View Assigned Orders
Update Delivery Status
View Route Optimization
ETA Tracking


🏗️ Tech Stack
Frontend

React.js
JSX
CSS
React Router
Axios

Backend

Spring Boot
Spring Security
JWT Authentication
JPA / Hibernate

Database

MySQL

Tools

Maven
Postman
Git & GitHub


📂 Project Structure
🔹 Backend Structure (Spring Boot)
com.wipro.smartpizza
│
├── config            → CORS configuration
├── controller        → REST APIs
├── dto               → Data Transfer Objects
├── entity            → Database Entities
├── repository        → JPA Repositories
├── security          → JWT & Security Config
├── service           → Business Logic
└── SmartpizzaApplication.java


🔹 Frontend Structure (React)
src/
│
├── components        → Reusable UI Components
├── pages             → Application Screens
├── services          → API Calls
├── routes            → Route Configuration
├── context           → Auth Context
├── styles            → CSS Files
├── App.js            → Main App
└── index.js          → Entry Point


🗂️ Database Tables

users
roles
pizza
cart
pizza_order
payment
delivery
coupon


🔐 Authentication & Security

JWT-based Authentication
Role-based Authorization (ADMIN / CUSTOMER / DELIVERY)
Password Encryption
Protected Routes


🔄 Application Flow
User Login/Register
        ↓
Browse Pizza Menu
        ↓
Add to Cart
        ↓
Apply Coupon
        ↓
Place Order
        ↓
Payment Processing
        ↓
Order Confirmation
        ↓
Delivery Tracking


🤖 AI Features

Personalized Pizza Recommendations
Order History Analysis
Smart Combo Suggestions
ETA Prediction
Customer Behavior Analytics


📊 System Modules

Authentication & User Management
Smart Ordering System
AI Recommendation Engine
Payment & Billing
Delivery Tracking & Optimization
Admin Analytics Dashboard


🧩 Diagrams Included

✅ Context Diagram
✅ DFD (Level 0 & Level 1)
✅ ER Diagram
✅ Class Diagram
✅ Sequence Diagram
✅ Use Case Diagram
✅ System Architecture Diagram


⚙️ Installation Steps
Backend Setup
Shellgit clone <repo-url>cd smartpizza-backendmvn spring-boot:runShow more lines
Frontend Setup
Shellcd smartpizza-frontendnpm installnpm startShow more lines

🔌 API Endpoints (Sample)


✅ Advantages

Scalable architecture
Secure system using JWT
Real-time delivery tracking
AI-powered recommendations
Modular and maintainable code


🔮 Future Enhancements

Google Maps Integration
Real AI/ML Model Integration
Push Notifications
Docker Deployment
Microservices Architecture