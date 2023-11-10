package com.nordeus.jobfair.auctionservice.auctionservice.api;

import com.nordeus.jobfair.auctionservice.auctionservice.domain.AuctionService;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.Auction;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.AuctionId;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.User;
import com.nordeus.jobfair.auctionservice.auctionservice.domain.model.UserId;
import lombok.AllArgsConstructor;
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

    @GetMapping("/active/{auctionId}")
    public Auction getAuctionById(@PathVariable AuctionId auctionId) {
        return auctionService.getAuction(auctionId);
    }


    @PostMapping("/joinAuction/{auctionId}/{userId}")
    public void userJoinAuction(@PathVariable String auctionId, @PathVariable String userId){
        User user = User.users.stream().filter(u -> u.getUserId().equals(userId)).findFirst().get();
        AuctionId auctionIdObj = new AuctionId(auctionId);

        auctionService.join(auctionIdObj, user);
    }

    @PostMapping("/newBid/{auctionId}/{userId}")
    public void newBid(@PathVariable String auctionId, @PathVariable String userId){
        UserId userIdObj = new UserId(userId);
        AuctionId auctionIdObj = new AuctionId(auctionId);

        auctionService.bid(auctionIdObj, userIdObj);
    }
}
