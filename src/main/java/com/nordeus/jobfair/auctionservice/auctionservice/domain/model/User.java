package com.nordeus.jobfair.auctionservice.auctionservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@AllArgsConstructor
public class User {

    // Hard-coded the list of users (managers) instead of database
    // We could also generate users in AuctionServiceImpl, but we will pretend that we have database with users
    public static final Collection<User> users = new ArrayList<>() {{
        add(new User(new UserId(), "Xavi", "Hernandez", "Barcelona"));
        add(new User(new UserId(), "Carlo", "Ancelotti", "Real Madrid"));
        add(new User(new UserId(), "Pep", "Guardiola", "Man City"));
        add(new User(new UserId(), "Igor", "Duljaj", "Partizan"));
        add(new User(new UserId(), "Luis", "Enrique", "PSG"));
        add(new User(new UserId(), "Simone", "Inzaghi", "Inter"));
        add(new User(new UserId(), "Massimiliano", "Allegri", "Juventus"));
        add(new User(new UserId(), "Rudi", "Garcia", "Napoli"));
        add(new User(new UserId(), "Edin", "Terzic", "Borussia Dortmund"));
    }};

    private UserId userId;
    private String name;
    private String lastName;
    private String teamName;

    public String getUserId() {
        return this.userId.getId();
    }

    public String getName() {
        return this.name;
    }

    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String toString() {
        return this.name + " " + this.lastName;
    }
}
