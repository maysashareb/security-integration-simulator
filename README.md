# 🚀 Parallel Security Integration Simulator

A **Java multithreaded project** that simulates security posture scans across **CSPM**, **SSPM**, and **OSPM** integrations.  
Designed to demonstrate **backend engineering**, **concurrency**, and **security logic** skills.

---

## 🔢 1. Key Features

1. **Parallel Scanning Engine** using ExecutorService  
2. **Retry Logic** on connection failures  
3. **Dynamic Severity Levels** (LOW → CRITICAL)  
4. **Risk Score Calculation** (0–100)  
5. **Randomized Misconfiguration Detection**  
6. **Scan Duration Measurement**  
7. **CSV Results Export**  
8. **Summary Report** after all scans  

---

## 🧩 2. Project Structure

src/
├── Main.java
├── model/
│ ├── IntegrationTarget.java
│ ├── IntegrationType.java
│ ├── ScanResult.java
│ └── Severity.java
└── engine/
├── ScanTask.java
└── ScannerEngine.java

---


## ▶️ 3. How to Run

1. Install **Java 17+** (Java 21 recommended)  
2. Clone the repository:  
   ```bash
   git clone https://github.com/maysashareb/security-integration-simulator.git
   
3. Open the project in IntelliJ IDEA
4. Run Main.java
5. View results in the console and in scan-results.csv

## Author
Maysa Abu Shareb
Security Engineering • Java Developer
