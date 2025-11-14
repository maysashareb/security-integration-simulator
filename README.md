🚀 *Parallel Security Integration Simulator*
A Java multithreaded project that simulates security posture scans across CSPM, SSPM, and OSPM integrations.
Designed to demonstrate backend engineering, concurrency, and security logic skills.
🔢 *1. Key Features*
Parallel Scanning Engine using ExecutorService
Retry Logic on connection failures
Dynamic Severity Levels (LOW → CRITICAL)
Risk Score Calculation (0–100)
Randomized Misconfiguration Detection
Scan Duration Measurement
CSV Results Export
Detailed Summary Report after all scans
🧩 *2. Project Structure*
src/
 ├── Main.java
 ├── model/
 │    ├── IntegrationTarget.java
 │    ├── IntegrationType.java
 │    ├── ScanResult.java
 │    └── Severity.java
 └── engine/
      ├── ScanTask.java
      └── ScannerEngine.java
▶️ *3. How to Run*
Install Java 17+ (Java 21 recommended)
Clone the repository:
git clone https://github.com/maysashareb/security-integration-simulator.git
Open the project in IntelliJ IDEA
Run the file Main.java
View results in the console and in:
scan-results.csv
📊 *4. Example Output*
Starting parallel security scans...
[WARN] Attempt 1 failed to connect to https://api.aws.example.com
--- Scan Results ---
AWS Prod → severity=HIGH, riskScore=95, duration=3197ms
👩‍💻* Author*
Maysa Abu Shareb
Security Engineering • Java Developer
