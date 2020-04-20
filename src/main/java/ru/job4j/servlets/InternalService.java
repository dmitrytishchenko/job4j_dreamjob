package ru.job4j.servlets;

public class InternalService {
    private final ExternalService externalService;

    public InternalService(final ExternalService externalService) {
        this.externalService = externalService;
    }

    public void doWork() {
        externalService.doMegaWork();
    }
}
