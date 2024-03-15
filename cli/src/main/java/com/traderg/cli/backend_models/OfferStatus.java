package com.traderg.cli.backend_models;

public class OfferStatus {
    private Long id;
    private String status; // Consider enums for controlled values

    // Constructor
    public OfferStatus() {}

    public OfferStatus(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
