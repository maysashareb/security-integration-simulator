package com.maysa.securitysim.engine;

import com.maysa.securitysim.model.IntegrationTarget;
import com.maysa.securitysim.model.ScanResult;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ScannerEngine implements AutoCloseable {

    private final ExecutorService executor;

    public ScannerEngine(int threadCount) {
        this.executor = Executors.newFixedThreadPool(threadCount);
    }

    public List<ScanResult> scanAll(List<IntegrationTarget> targets) {
        List<Future<ScanResult>> futures = new ArrayList<>();
        for (IntegrationTarget target : targets) {
            futures.add(executor.submit(new ScanTask(target)));
        }

        List<ScanResult> results = new ArrayList<>();
        for (Future<ScanResult> future : futures) {
            try {
                results.add(future.get());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                System.err.println("Error during scan: " + e.getMessage());
            }
        }
        return results;
    }

    public void exportToCsv(List<ScanResult> results, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("name,type,endpoint,success,issues,severity,riskScore,durationMs,summary\n");
            for (ScanResult result : results) {
                IntegrationTarget t = result.getTarget();
                writer.write(String.format(
                        "%s,%s,%s,%s,%d,%s,%d,%d,%s%n",
                        t.getName(),
                        t.getType(),
                        t.getEndpoint(),
                        result.isSuccess(),
                        result.getIssuesFound(),
                        result.getSeverity(),
                        result.getRiskScore(),
                        result.getDurationMillis(),
                        result.getSummary().replace(",", ";")
                ));
            }
        }
    }


    @Override
    public void close() {
        executor.shutdown();
    }
}
