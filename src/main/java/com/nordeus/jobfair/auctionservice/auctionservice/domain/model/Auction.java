package com.nordeus.jobfair.auctionservice.auctionservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;


@Getter
@AllArgsConstructor
public class Auction {

    public static Collection<Auction> auctions = new ArrayList<>();
    // TODO: set it to AuctinId type
    private final String auctionId;
    private int highestBid;
    private User winner;
    private boolean isActive;
    private Player player; // football player which the auction is for
    private Collection<User> users = new ArrayList<>(); // managers (users)
    private  int auctionTime;
    private final int auctionLength = 60;

    public Auction(Player player) {
        this.auctionId = new AuctionId().getId();
        this.highestBid = 1;
        this.winner = null;
        this.player = player;
        this.isActive = true;
        this.auctionTime = this.auctionLength;
    }

    public String getAuctionId() {
        return this.auctionId;
    }

    public int getHighestBid() {
        return highestBid;
    }

    public User getWinner() {
        return winner;
    }

    public int getAuctionTime() {
        return auctionTime;
    }

    public Player getPlayer() {
        return player;
    }

//    public int getAuctionLength() {
//        return auctionLength;
//    }

    public Collection<User> getUsers() {
        return users;
    }

    public boolean isActive() {
        return isActive;
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
        isActive = active;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public String toString() {
        return "ID: " + this.auctionId + ". For player:  " + this.player.toString();
    }
}
