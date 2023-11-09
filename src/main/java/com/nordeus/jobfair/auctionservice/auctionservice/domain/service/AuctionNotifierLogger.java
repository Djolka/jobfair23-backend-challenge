package com.nordeus.jobfair.auctionservice.auctionservice.domain.service;

import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.Auction;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.Bid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;
import java.util.Collection;

@Slf4j
@Service
public class AuctionNotifierLogger implements AuctionNotifer {

    Logger logger
            = Logger.getLogger(
            AuctionNotifierLogger.class.getName());

    @Override
    public void auctionFinished(Auction auction) {
        logger.info("Auction finished: " + auction);
    }

    @Override
    public void bidPlaced(Bid bid) {
//        log.info("Bid placed: {}", bid);
    }

    @Override
    public void activeAuctionsRefreshed(Collection<Auction> activeAuctions) {
//        log.info("Active auctions are refreshed: {}", activeAuctions);
    }
}