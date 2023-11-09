package com.nordeus.jobfair.auctionservice.auctionservice.domain;

import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.*;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.service.AuctionNotifer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class AuctionServiceImpl extends TimerTask implements AuctionService {

    private final AuctionNotifer auctionNotifer;

    Logger logger
            = Logger.getLogger(
            AuctionServiceImpl.class.getName());

    private Timer timer;
    private int seconds = 0;

    @Autowired
    public AuctionServiceImpl(AuctionNotifer auctionNotifer) {
        generateNewAuctions();

        this.auctionNotifer = auctionNotifer;
        this.timer = new Timer();
        long delay = 0; // Delay before the first execution (0 for immediate execution)
        long period = 1000; // Period in ms
        timer.scheduleAtFixedRate(this, delay, period);
    }

    @Override
    public Collection<Auction> getAllActive() {
        return Auction.auctions;
    }

    @Override
    public Auction getAuction(AuctionId auctionId) {
        // find Auction with auctionId
        return Auction.auctions.stream().filter((auction -> auction.getAuctionId().equals(auctionId.getId()))).findAny().get();
    }

    @Override
    public void join(AuctionId auctionId, User user) {
//        // Find Auction with auctionId
        Optional<Auction> optionalAuction = Auction.auctions.stream()
                .filter(auction -> auction.getAuctionId().equals(auctionId.getId()))
                .findAny();

        if (optionalAuction.isPresent()) {
            Auction auction = optionalAuction.get();
            auction.addUser(user);
            logger.info("New user: " + user.toString() + " added to auction:" + auction.toString());
        } else {
//            logger.warning("Auction not found for AuctionId: " + auctionId);
            throw new NoSuchElementException("Auction not found for AuctionId: " + auctionId);
        }
    }

    @Override
    public void bid(AuctionId auctionId, UserId userId) {

        Optional<Auction> optionalAuction = Auction.auctions.stream()
                .filter(auction -> auction.getAuctionId().equals(auctionId.getId()))
                .findAny();

        Optional<User> optionalUser = User.users.stream()
                .filter(user-> user.getUserId().equals(userId.getId()))
                .findAny();

        if (optionalAuction.isPresent() && optionalUser.isPresent()) {
            Auction auction = optionalAuction.get();
            User user = optionalUser.get();

            if(auction.isActive()) {
                auction.setWinner(user);

                auction.setHighestBid(auction.getHighestBid()+1);

                // TODO: add time to auction if less than 5 seconds until the end

                auctionNotifer.bidPlaced(new Bid(user, auction));
            }else {
                throw new IllegalStateException("Aukcija: " + auction.toString() + " is not active!");
            }
        }else {
            throw new NoSuchElementException("error");
        }
    }


    // Generate 10 auctions every minute
    @Override
    public void run(){
        this.seconds += 1;
        logger.info(String.valueOf(this.seconds));

        if(this.seconds == 10) {
            generateNewAuctions();
            this.seconds = 0;
        }
    }

    private void generateNewAuctions() {
        logger.info("generating new auctions");
        List<Player> notAuctionedPlayers = new ArrayList<>(Player.players.stream().filter((player -> !player.isOnAuction())).toList());
        // if not enough not auctioned players (10) then don't generate new Auctions
        if(notAuctionedPlayers.size() < 10) {
                logger.info(String.valueOf(notAuctionedPlayers.size()));
                timer.cancel();
                return;
        }

        for (int i = 0;i < 10;i++) {
            Collections.shuffle(notAuctionedPlayers);
            Player playerForAuction = notAuctionedPlayers.get(0);
            notAuctionedPlayers.remove(0);
            playerForAuction.setOnAuction(true);
            logger.info(playerForAuction.toString());
            Auction newAuction = new Auction(playerForAuction);
            Auction.auctions.add(newAuction);
        }
    }
}
