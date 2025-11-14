package com.maysa.securitysim.model;

public class ScanResult {

    private final IntegrationTarget target;
    private final boolean success;
    private final int issuesFound;
    private final String summary;
    private final Severity severity;
    private final int riskScore;       // 0 - 100
    private final long durationMillis; // scan duration

    public ScanResult(IntegrationTarget target,
                      boolean success,
                      int issuesFound,
                      String summary,
                      Severity severity,
                      int riskScore,
                      long durationMillis) {
        this.target = target;
        this.success = success;
        this.issuesFound = issuesFound;
        this.summary = summary;
        this.severity = severity;
        this.riskScore = riskScore;
        this.durationMillis = durationMillis;
    }

    // 👇 add this
    public ScanResult(IntegrationTarget target,
                      boolean success,
                      int issuesFound,
                      String summary) {
        this(
                target,
                success,
                issuesFound,
                summary,
                Severity.LOW,
                0,
                0L
        );
    }

    public IntegrationTarget getTarget() { return target; }
    public boolean isSuccess() { return success; }
    public int getIssuesFound() { return issuesFound; }
    public String getSummary() { return summary; }
    public Severity getSeverity() { return severity; }
    public int getRiskScore() { return riskScore; }
    public long getDurationMillis() { return durationMillis; }

    @Override
    public String toString() {
        return "ScanResult{" +
                "target=" + target +
                ", success=" + success +
                ", issuesFound=" + issuesFound +
                ", severity=" + severity +
                ", riskScore=" + riskScore +
                ", durationMillis=" + durationMillis +
                ", summary='" + summary + '\'' +
                '}';
    }
}
