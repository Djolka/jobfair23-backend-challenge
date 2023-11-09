package com.nordeus.jobfair.auctionservice.auctionservice.domain.model;

import java.util.UUID;

public class AuctionId {

    private final String id;

    public AuctionId() {
        this.id = UUID.randomUUID().toString();
    }

    public AuctionId(String auctionId) {
        this.id = auctionId;
    }

    public String getId() {
        return id;
    }
}
