package com.nordeus.jobfair.auctionservice.auctionservice.domain.model;
import java.util.UUID;

public class UserId {
    private final String id;

    public UserId() {
        this.id = UUID.randomUUID().toString();
    }

    public UserId(String userId) {
        this.id = userId;
    }

    public String getId() {
        return this.id;
    }
}
