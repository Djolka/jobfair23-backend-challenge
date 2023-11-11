package com.nordeus.jobfair.auctionservice.auctionservice.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.AuctionService;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.Auction;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.AuctionId;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.User;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.UserId;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/auctions")
public class HttpController {

    private AuctionService auctionService;

    @GetMapping("/active")
    public Collection<Auction> getAllActive() {
        return auctionService.getAllActive();
    }

    // for testing
    @GetMapping("/users")
    public Collection<User> getUsers() {
        return User.users;
    }

    @GetMapping(value = "/active/{auctionId}")
    public ResponseEntity<String> getAuctionById(@PathVariable AuctionId auctionId) {
        Auction auction = auctionService.getAuction(auctionId);
        if (auction == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("{ \"message\": \"Auction does not exist.\"}");
        }

        String jsonAuction;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonAuction = objectMapper.writeValueAsString(auction);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(jsonAuction);
    }

    @PostMapping("/joinAuction/{auctionId}/{userId}")
    public ResponseEntity<String> userJoinAuction(@PathVariable String auctionId, @PathVariable String userId) {
        Optional<User> user = User.users.stream().filter(u -> u.getUserId().equals(userId)).findFirst();
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist.");
        }

        AuctionId auctionIdObj = new AuctionId(auctionId);

        return auctionService.join(auctionIdObj, user.get());
    }

    @PostMapping("/newBid/{auctionId}/{userId}")
    public ResponseEntity<String> newBid(@PathVariable String auctionId, @PathVariable String userId) {
        UserId userIdObj = new UserId(userId);
        AuctionId auctionIdObj = new AuctionId(auctionId);

        return auctionService.bid(auctionIdObj, userIdObj);
    }
}
