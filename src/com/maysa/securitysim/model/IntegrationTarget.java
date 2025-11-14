package com.maysa.securitysim.model;

public class IntegrationTarget {

    private final String name;
    private final IntegrationType type;
    private final String endpoint;

    public IntegrationTarget(String name, IntegrationType type, String endpoint) {
        this.name = name;
        this.type = type;
        this.endpoint = endpoint;
    }

    public String getName() {
        return name;
    }

    public IntegrationType getType() {
        return type;
    }

    public String getEndpoint() {
        return endpoint;
    }

    @Override
    public String toString() {
        return "IntegrationTarget{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", endpoint='" + endpoint + '\'' +
                '}';
    }
}
