package com.nordeus.jobfair.auctionservice.auctionservice.domain.service;

import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.Auction;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.Bid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuctionNotifierLogger implements AuctionNotifer {

    @Override
    public void auctionFinished(Auction auction, boolean hasWinner) {
        if (hasWinner) {
            log.info("Auction for the player: " + auction.getPlayer() + " is finished. The winner is " + auction.getWinner() +
                    " with the bid of " + auction.getHighestBid() + " tokens.");
        } else {
            log.info("Auction for the player: " + auction.getPlayer() + " is finished. There is no winner.");
        }
    }

    @Override
    public void bidPlaced(Bid bid) {
        log.info(bid.getUser() + " placed a new bid of " + bid.getAuction().getHighestBid() + " tokens for player:  " + bid.getAuction().getPlayer() +
                ". Time remaining: " + bid.getAuction().getAuctionTime() + " seconds.");
    }

    @Override
    public void activeAuctionsRefreshed(Auction activeAuction) {
        log.info("New auction * " + activeAuction.toString());
    }
}