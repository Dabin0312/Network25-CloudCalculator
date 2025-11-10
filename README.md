# Network25-CloudCalculator

Java Socket Programming Calculator (Client–Server Project for Computer Network)

## 🧩 Overview
This project implements a simple calculator using Java sockets.  
- The **Server** listens for multiple clients concurrently using a thread pool, runable.  
- The **Client** sends arithmetic commands (ADD, SUB, MUL, DIV).  
- The server processes the command and returns a result or an error message.

## ⚙️How to Run
1. Start `Server.java` first  
2. Then run one or more `Client.java` programs  
3. Enter commands like:
ex) ADD 10 20
SUB 50 12
DIV 25 5
4. Type `EXIT` to quit the client.

## 💾 Config
The file `server_info.dat` contains:
localhost 1234

You can change it if you want to connect to a remote server.

## 🚀Features
- Multi-client support using ExecutorService  
- Error handling for invalid commands and division by zero  
- Simple text-based protocol  

## 📚 Example Protocol
| Client Input | Server Response |
|---------------|----------------|
| ADD 10 20 | RESULT 30 |
| DIV 5 0 | ERROR DIV_ZERO |
| SUB 9 2 | RESULT 7 |

## 👤 Author
Dabin0312  
Gachon University – AI Major


