package com.nordeus.jobfair.auctionservice.auctionservice.domain;

import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.*;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.service.AuctionNotifer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuctionServiceImpl extends TimerTask implements AuctionService {

    private final AuctionNotifer auctionNotifer;

    Logger logger
            = Logger.getLogger(
            AuctionServiceImpl.class.getName());

    private Timer timer;
    private int seconds = 0;
    private final int numberOfAuctionToGenerate = 10;
    private final int generateAuctionsInterval = 60;

    @Autowired
    public AuctionServiceImpl(AuctionNotifer auctionNotifer) {
        this.auctionNotifer = auctionNotifer;
        this.timer = new Timer();
        long delay = 0; // Delay before the first execution (0 for immediate execution)
        long period = 1000; // Period in ms
        this.generateNewAuctions();
        this.timer.scheduleAtFixedRate(this, delay, period);
    }

    @Override
    public Collection<Auction> getAllActive() {
        return Auction.auctions.stream().filter(Auction::isActive).collect(Collectors.toList());
    }

    @Override
    public Auction getAuction(AuctionId auctionId) {
        return Auction.auctions.stream().filter((auction -> auction.getAuctionId().equals(auctionId.getId()))).findAny().get();
    }

    @Override
    public void join(AuctionId auctionId, User user) {
        Auction auction = this.getAuction(auctionId);
        auction.addUser(user);
    }

    @Override
    public void bid(AuctionId auctionId, UserId userId) {
        Auction auction = this.getAuction(auctionId);

        User user = User.users.stream()
                .filter(u -> u.getUserId().equals(userId.getId()))
                .findAny().get();

        if(!auction.getUsers().contains(user)) {
            throw new IllegalStateException("You must first join the auction to be able to bid");
        }

        if(auction.isActive()) {
            auction.setWinner(user);
            auction.setHighestBid(auction.getHighestBid()+1);
            if(auction.getAuctionTime() < 5) {
                auction.setAuctionTime(5);
            }

            this.auctionNotifer.bidPlaced(new Bid(user, auction));
        }else {
            throw new IllegalStateException("Auction: " + auction.toString() + " is not active!");
        }

    }


    // Generate 10 auctions every minute
    @Override
    public void run(){
        // find all active
        Collection<Auction> activeAuctions = this.getAllActive();
        for(Auction a : activeAuctions) {
            a.setAuctionTime(a.getAuctionTime()-1);

            if(a.getAuctionTime() == 0) {
                a.setActive(false);
                this.auctionNotifer.auctionFinished(a, a.getWinner() != null);
            }
        }

        this.seconds += 1;
        if(this.seconds == this.generateAuctionsInterval) {
            this.generateNewAuctions();
            this.seconds = 0;
        }
    }

    private void generateNewAuctions() {
        List<Player> notAuctionedPlayers = new ArrayList<>(Player.players.stream().filter((player -> !player.isOnAuction())).toList());

        // if not enough not auctioned players (10) then don't generate new Auctions
        if(notAuctionedPlayers.size() < this.numberOfAuctionToGenerate) {
            return;
        }

        for (int i = 0; i < this.numberOfAuctionToGenerate; i++) {
            Collections.shuffle(notAuctionedPlayers);

            Player playerForAuction = notAuctionedPlayers.get(0);
            notAuctionedPlayers.remove(0);
            playerForAuction.setOnAuction(true);

            Auction newAuction = new Auction(playerForAuction);
            Auction.auctions.add(newAuction);

            this.auctionNotifer.activeAuctionsRefreshed(newAuction);
        }
    }
}
