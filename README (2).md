# 💬 Multi-Client P2P Chat Application

A fully functional **Peer-to-Peer Chat Application** built using core Java networking libraries — no frameworks, no shortcuts.

---

## ⚙️ Technical Architecture

| Component | Detail |
|-----------|--------|
| **Protocol** | TCP/IP (reliable, connection-oriented) |
| **Port** | 1234 (custom socket binding) |
| **Concurrency** | Dedicated thread per client (ClientHandler pattern) |
| **Message Routing** | Server-side broadcast engine |
| **Connection Handling** | Graceful disconnect with resource cleanup |

---

## 📁 Project Structure

```
P2P-Chat-App/
│
├── Server.java         # Main server — accepts client connections
├── ClientHandler.java  # Handles each client in a separate thread
└── Client.java         # Client-side — send & receive messages
```

---

## 🚀 How to Run

### Step 1 — Compile all files
```bash
javac Server.java ClientHandler.java Client.java
```

### Step 2 — Start the Server
```bash
java Server
```

### Step 3 — Start Client(s) (open multiple terminals)
```bash
java Client
```

> You can open as many client terminals as you want — all will chat in real time!

---

## 🧠 Problems Solved

**Problem:** How to handle multiple clients simultaneously without blocking?
**Solution:** Spawned individual threads per client using Java's `Thread` class

**Problem:** How to route messages to all recipients?
**Solution:** Maintained a synchronized list of active `PrintWriter` streams and broadcast to all

**Problem:** How to handle abrupt client disconnections?
**Solution:** Wrapped socket reads in `try-catch` with `finally` block for cleanup

---

## 📊 Outcome

- ✅ Real-time message delivery across multiple clients
- ✅ Zero message loss during testing
- ✅ Clean server logs with join/leave notifications
- ✅ Scalable architecture — new clients connect without restarting server

---

## 🛠 Tech Stack

`Java` · `TCP/IP` · `Socket Programming` · `Multithreading` · `OOP`

---

## 👨‍💻 Author

**[Your Name]** — Network Engineering Fresher | B.Tech CSE 2026  
[LinkedIn](https://linkedin.com/in/yourprofile) · [GitHub](https://github.com/yourusername)
