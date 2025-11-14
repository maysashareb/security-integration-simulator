package com.maysa.securitysim;

import com.maysa.securitysim.engine.ScannerEngine;
import com.maysa.securitysim.model.IntegrationTarget;
import com.maysa.securitysim.model.IntegrationType;
import com.maysa.securitysim.model.ScanResult;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        // Define example integrations
        List<IntegrationTarget> targets = Arrays.asList(
                new IntegrationTarget("AWS Prod", IntegrationType.CSPM, "https://api.aws.example.com"),
                new IntegrationTarget("Azure Dev", IntegrationType.CSPM, "https://api.azure.example.com"),
                new IntegrationTarget("Okta Tenant", IntegrationType.SSPM, "https://api.okta.example.com"),
                new IntegrationTarget("On-Prem AD", IntegrationType.OSPM, "ldaps://ad.internal.local")
        );

        System.out.println("Starting parallel security scans for " + targets.size() + " integrations...");

        long globalStart = System.currentTimeMillis();
        List<ScanResult> results;
        try (ScannerEngine engine = new ScannerEngine(4)) {
            results = engine.scanAll(targets);
        }
        long globalDuration = System.currentTimeMillis() - globalStart;

        System.out.println("\n--- Scan Results ---");
        for (ScanResult result : results) {
            System.out.println(result);
        }

        // Export CSV
        String output = "scan-results.csv";
        new ScannerEngine(1).exportToCsv(results, output);
        System.out.println("\nResults exported to: " + output);

        // Print summary statistics
        printSummary(results, globalDuration);
    }

    private static void printSummary(List<ScanResult> results, long totalDurationMs) {
        long successCount = results.stream().filter(ScanResult::isSuccess).count();
        long failureCount = results.size() - successCount;
        int totalIssues = results.stream().mapToInt(ScanResult::getIssuesFound).sum();
        double avgIssues = results.isEmpty() ? 0.0 : (double) totalIssues / results.size();
        double avgRisk = results.isEmpty() ? 0.0 :
                results.stream().mapToInt(ScanResult::getRiskScore).average().orElse(0.0);

        System.out.println("\n=== Summary ===");
        System.out.println("Total targets:      " + results.size());
        System.out.println("Successful scans:   " + successCount);
        System.out.println("Failed scans:       " + failureCount);
        System.out.println("Total issues found: " + totalIssues);
        System.out.printf("Average issues/target: %.2f%n", avgIssues);
        System.out.printf("Average risk score:    %.2f%n", avgRisk);
        System.out.println("Total scan time (ms):  " + totalDurationMs);
    }
}
