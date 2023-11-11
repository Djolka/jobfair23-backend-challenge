package com.nordeus.jobfair.auctionservice.auctionservice.domain;

import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.*;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.service.AuctionNotifer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AuctionServiceImpl extends TimerTask implements AuctionService {

    private final AuctionNotifer auctionNotifer;
    private int seconds = 0;

    @Autowired
    public AuctionServiceImpl(AuctionNotifer auctionNotifer) {
        this.auctionNotifer = auctionNotifer;
        long delay = 0; // Delay before the first execution (0 for immediate execution)
        long period = 1000; // Period in ms
        this.generateNewAuctions();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, delay, period);
    }

    @Override
    public Collection<Auction> getAllActive() {
        return  Auction.auctions.stream().filter(Auction::isActive).toList();
    }

    @Override
    public Auction getAuction(AuctionId auctionId) {
        Optional<Auction> auction = Auction.auctions.stream().filter((a -> a.getAuctionId().equals(auctionId.getId()))).findAny();
        return auction.orElse(null);
    }

    @Override
    public ResponseEntity<String> join(AuctionId auctionId, User user) {
        Auction auction = this.getAuction(auctionId);
        if (auction == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no auction with this id.");
        }

        if (auction.getUsers().contains(user)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User already joined this auction.");
        }

        auction.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("You have successfully joined auction for player: " + auction.getPlayer());
    }

    @Override
    public ResponseEntity<String> bid(AuctionId auctionId, UserId userId) {
        Auction auction = this.getAuction(auctionId);
        if (auction == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no auction with this id.");
        }

        Optional<User> userOpt = User.users.stream()
                .filter(u -> u.getUserId().equals(userId.getId()))
                .findAny();
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("There is no user with this id.");
        }

        User user = userOpt.get();

        if (!auction.getUsers().contains(user)) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("You must first join the auction to be able to bid.");
        }

        if (!auction.isActive()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Auction is not active.");
        }

        auction.setWinner(user);
        auction.setHighestBid(auction.getHighestBid() + 1);
        if (auction.getAuctionTime() < 5) {
            auction.setAuctionTime(5);
        }

        this.auctionNotifer.bidPlaced(new Bid(user, auction));

        return ResponseEntity.status(HttpStatus.OK).body("You have successfully made a bid.");
    }

    // Generate 10 auctions every minute
    @Override
    public void run() {
        // find all active
        Collection<Auction> activeAuctions = this.getAllActive();
        for (Auction a : activeAuctions) {
            a.setAuctionTime(a.getAuctionTime() - 1);

            if (a.getAuctionTime() == 0) {
                a.setActive(false);
                if (a.getWinner() == null) {
                    a.getPlayer().setForAuction(true);
                }
                this.auctionNotifer.auctionFinished(a, a.getWinner() != null);
            }
        }

        this.seconds += 1;
        int generateAuctionsInterval = 60;
        if (this.seconds == generateAuctionsInterval) {
            this.generateNewAuctions();
            this.seconds = 0;
        }
    }

    private void generateNewAuctions() {
        List<Player> notAuctionedPlayers = new ArrayList<>(Player.players.stream().filter((Player::isForAuction)).toList());

        // if not enough not auctioned players (10) then don't generate new Auctions
        int numberOfAuctionToGenerate = 10;
        if (notAuctionedPlayers.size() < numberOfAuctionToGenerate) {
            return;
        }

        for (int i = 0; i < numberOfAuctionToGenerate; i++) {
            Collections.shuffle(notAuctionedPlayers);

            Player playerForAuction = notAuctionedPlayers.get(0);
            notAuctionedPlayers.remove(0);
            playerForAuction.setForAuction(false);

            Auction newAuction = new Auction(playerForAuction);
            Auction.auctions.add(newAuction);

            this.auctionNotifer.activeAuctionsRefreshed(newAuction);
        }
    }
}
