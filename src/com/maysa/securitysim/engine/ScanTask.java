package com.maysa.securitysim.engine;

import com.maysa.securitysim.model.IntegrationTarget;
import com.maysa.securitysim.model.ScanResult;
import com.maysa.securitysim.model.Severity;

import java.util.Random;
import java.util.concurrent.Callable;

public class ScanTask implements Callable<ScanResult> {

    private final IntegrationTarget target;
    private final Random random = new Random();

    public ScanTask(IntegrationTarget target) {
        this.target = target;
    }

    @Override
    public ScanResult call() throws Exception {
        long start = System.currentTimeMillis();

        // Try once, if fail, retry one more time
        boolean success = false;
        int issues = 0;
        String summary = "";

        for (int attempt = 1; attempt <= 2; attempt++) {
            // Simulate variable latency
            int delay = 400 + random.nextInt(1400);
            Thread.sleep(delay);

            // 20% chance of network failure per attempt
            boolean failure = random.nextInt(10) < 2;

            if (failure) {
                String attemptMsg = "Attempt " + attempt + " failed to connect to " + target.getEndpoint();
                System.out.println("[WARN] " + attemptMsg);
                summary = attemptMsg;
                if (attempt == 2) {
                    long duration = System.currentTimeMillis() - start;
                    return new ScanResult(
                            target,
                            false,
                            0,
                            "All retry attempts failed for " + target.getName(),
                            Severity.CRITICAL,
                            90,
                            duration
                    );
                }
            } else {
                // Simulate successful scan with findings
                success = true;
                issues = random.nextInt(6); // 0 - 5 issues
                break;
            }
        }

        Severity severity = calculateSeverity(issues);
        int riskScore = calculateRiskScore(issues, severity);

        if (!success) {
            summary = "Unknown failure for " + target.getName();
        } else if (issues == 0) {
            summary = "No misconfigurations detected.";
        } else {
            summary = "Detected " + issues + " potential misconfigurations.";
        }

        long duration = System.currentTimeMillis() - start;

        return new ScanResult(
                target,
                success,
                issues,
                summary,
                severity,
                riskScore,
                duration
        );
    }

    private Severity calculateSeverity(int issues) {
        if (issues == 0) return Severity.LOW;
        if (issues == 1) return Severity.MEDIUM;
        if (issues <= 3) return Severity.HIGH;
        return Severity.CRITICAL;
    }

    private int calculateRiskScore(int issues, Severity severity) {
        int base = issues * 15; // each issue adds base risk
        int sevBonus;
        switch (severity) {
            case LOW -> sevBonus = 10;
            case MEDIUM -> sevBonus = 30;
            case HIGH -> sevBonus = 50;
            case CRITICAL -> sevBonus = 70;
            default -> sevBonus = 0;
        }
        int score = base + sevBonus;
        if (score > 100) score = 100;
        return score;
    }
}
