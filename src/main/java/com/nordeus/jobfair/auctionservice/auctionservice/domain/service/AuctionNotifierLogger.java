package com.nordeus.jobfair.auctionservice.auctionservice.domain.service;

import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.Auction;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.Bid;
// import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;
//import java.util.Collection;

// @Slf4j
@Service
public class AuctionNotifierLogger implements AuctionNotifer {

    Logger logger
            = Logger.getLogger(
            AuctionNotifierLogger.class.getName());

    @Override
    public void auctionFinished(Auction auction, boolean hasWinner) {
        if(hasWinner) {
            logger.info("Auction for the player: " + auction.getPlayer() + " is finished. The winner is " + auction.getWinner() +
                    " with the bid of " + auction.getHighestBid() + " tokens.");
        }else {
            logger.info("Auction for the player: " + auction.getPlayer() + "is finished. There is no winner.");
        }
    }

    @Override
    public void bidPlaced(Bid bid) {
//        log.info("Bid placed: {}", bid);
        logger.info(bid.getUser() + " placed a new bid of " + bid.getAuction().getHighestBid() + " tokens for player:  " + bid.getAuction().getPlayer() +
                ".Time remaining: " + bid.getAuction().getAuctionTime());
    }

    @Override
    public void activeAuctionsRefreshed(Auction activeAuction) {
        logger.info("New auction: " + activeAuction.toString());
    }
}