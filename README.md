# 💬 Multi-Client P2P Chat Application

A fully functional Peer-to-Peer Chat Application built using core Java networking libraries — no frameworks, no shortcuts.

## ⚙️ Technical Architecture

| Component | Detail |
|-----------|--------|
| Protocol | TCP/IP (reliable, connection-oriented) |
| Port | 1234 (custom socket binding) |
| Concurrency | Dedicated thread per client (ClientHandler pattern) |
| Message Routing | Server-side broadcast engine |
| Connection Handling | Graceful disconnect with resource cleanup |

## 📁 Project Structure

Server.java         → Main server — accepts client connections
ClientHandler.java  → Handles each client in a separate thread
Client.java         → Client-side — send & receive messages

## 🚀 How to Run

Step 1 — Compile:
javac Server.java ClientHandler.java Client.java

Step 2 — Start Server:
java Server

Step 3 — Start Client (open multiple terminals):
java Client

## 🧠 Problems Solved

Problem: Multiple clients simultaneously without blocking?
Solution: Spawned individual threads per client using Java Thread class

Problem: Route messages to all recipients?
Solution: Synchronized list of active PrintWriter streams — broadcast to all

Problem: Handle abrupt disconnections?
Solution: try-catch with finally block for cleanup

## 📊 Outcome

✅ Real-time message delivery across multiple clients
✅ Zero message loss during testing
✅ Clean server logs with join/leave notifications
✅ Scalable — new clients connect without restarting server

## 🛠 Tech Stack

Java | TCP/IP | Socket Programming | Multithreading | OOP

## 👨‍💻 Author

B.Tech CSE 2026 — Noida Institute of Engineering & Technology
