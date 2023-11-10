package com.nordeus.jobfair.auctionservice.auctionservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;


@Getter
@AllArgsConstructor
public class Auction {

    public static Collection<Auction> auctions = new ArrayList<>();
    private final AuctionId auctionId;
    private int highestBid;
    private User winner;
    private boolean isActive;
    private Player player; // football player which the auction is for
    private Collection<User> users = new ArrayList<>(); // managers (users)
    private  int auctionTime;
    private final int auctionLength = 60;

    public Auction(Player player) {
        this.auctionId = new AuctionId();
        this.highestBid = 1;
        this.winner = null;
        this.player = player;
        this.isActive = true;
        this.auctionTime = this.auctionLength;
    }

    public String getAuctionId() {
        return this.auctionId.getId();
    }

    public int getHighestBid() {
        return this.highestBid;
    }

    public User getWinner() {
        return this.winner;
    }

    public int getAuctionTime() {
        return this.auctionTime;
    }

    public Player getPlayer() {
        return this.player;
    }

//    public int getAuctionLength() {
//        return auctionLength;
//    }

    public Collection<User> getUsers() {
        return this.users;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public void setHighestBid(int highestBid) {
        this.highestBid = highestBid;
    }

    public void setAuctionTime(int auctionTime) {
        this.auctionTime = auctionTime;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public String toString() {
        return "ID: " + this.auctionId.getId() + ". For player:  " + this.player.toString();
    }
}
