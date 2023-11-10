package com.nordeus.jobfair.auctionservice.auctionservice.domain.service;

import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.Auction;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.Bid;

import java.util.Collection;

public interface AuctionNotifer {

    void auctionFinished(Auction auction, boolean hasWinner);

    void bidPlaced(Bid bid);

    void activeAuctionsRefreshed(Auction activeAuctions);
}
