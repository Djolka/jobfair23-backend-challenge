package com.nordeus.jobfair.auctionservice.auctionservice.domain.model;

public class Bid {

    private final User user;
    private final Auction auction;

    public Bid(User user, Auction auction) {
        this.user = user;
        this.auction = auction;
    }

    @Override
    public String toString() {
        return this.user.getName() + " " + this.user.getLastName() + " set new highest bid: " + this.auction.getHighestBid();
    }

    public User getUser() {
        return user;
    }

    public Auction getAuction() {
        return auction;
    }
}
