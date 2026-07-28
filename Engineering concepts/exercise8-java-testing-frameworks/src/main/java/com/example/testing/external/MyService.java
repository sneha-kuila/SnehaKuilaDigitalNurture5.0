package com.example.testing.external;

public class MyService {

    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    // Doc 6 - Exercise 3: argument matching target method
    public String fetchDataFor(String userId) {
        return externalApi.getData() + ":" + userId;
    }

    // Doc 6 - Exercise 4: void method usage
    public void performAction() {
        externalApi.performAction();
    }

    // Doc 6 - Exercise 7: void method that can throw
    public void performRiskyAction() {
        externalApi.performAction();
    }
}
