<p align="center">
  <img src="banner.png" alt="Parallel Security Integration Simulator Banner" width="100%">
</p>

Parallel Security Integration Simulator
A multithreaded Java project that simulates security posture scans across CSPM, SSPM, and OSPM environments.

 Overview:
 
The Parallel Security Integration Simulator is a Java-based tool that mimics how modern security platforms (XM Cyber, Wiz, Prisma Cloud, Orca, etc.) run security posture scans across different types of integrations:
CSPM – Cloud Security Posture Management
SSPM – SaaS Security Posture Management
OSPM – On-Prem Security Posture Management
Each integration target is scanned in parallel using Java’s concurrency features.
The simulator produces realistic security findings, including:
Connection failures
Retry attempts
Random misconfiguration counts
Severity levels (LOW → CRITICAL)
Risk score (0–100)
Scan duration per target
CSV report generation
This project showcases skills in:
✔ Backend development
✔ Java concurrency
✔ System design
✔ Security simulation logic
✔ File reporting & analytics

Key Features:

🔹 Parallel Scanning Engine
Runs multiple security scans simultaneously using:
ExecutorService executor = Executors.newFixedThreadPool(nThreads);
🔹 Resilient Retry Logic
Each integration automatically retries if a network failure occurs:
Simulated 20% random failure chance
One retry attempt per scan
🔹 Dynamic Severity & Risk Scoring
Misconfigurations automatically map to:
Severity: LOW / MEDIUM / HIGH / CRITICAL
Risk Score: weighted calculation up to 100
🔹 Scan Duration Measurement
Every scan includes full execution timing:
long duration = System.currentTimeMillis() - start;
🔹 CSV Report Generation
All results are exported to:
scan-results.csv
Including:
Target name
Integration type
Endpoint
Issues found
Severity
Risk score
Duration

Summary:

🔹 Comprehensive Summary Output
After all scans, the program prints:
Total targets
Successful scans
Failed scans
Total issues found
Average issues per target
Average risk score
Total scan time
▶️ How to Run

Requirements:

Java 17+ (Java 21 recommended)
IntelliJ IDEA (or any Java IDE)

Steps:

Clone the repository:
git clone https://github.com/maysashareb/security-integration-simulator.git
Open the project in IntelliJ.
Run the Main class.
View results in the console and in:
scan-results.csv
