package com.nordeus.jobfair.auctionservice.auctionservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@AllArgsConstructor
public class User {

    // Hard-coded the list of users (managers) instead of database
    public static final Collection<User> users = new ArrayList<User>(){{
        add(new User("b050fd8a-1d60-4b49-9a88-05927c9f1111", "Xavi", "Hernandez", "Barcelona"));
        add(new User("b050fd8a-1d60-4b49-9a88-05927c9f2222", "Carlo", "Ancelotti", "Real Madrid"));
        add(new User("b050fd8a-1d60-4b49-9a88-05927c9f3333", "Pep", "Guardiola", "Man City"));
        add(new User("b050fd8a-1d60-4b49-9a88-05927c9f4444", "Igor", "Duljaj", "Partizan"));
        add(new User("b050fd8a-1d60-4b49-9a88-05927c9f5555", "Luis", "Enrique", "PSG"));
        add(new User("b050fd8a-1d60-4b49-9a88-05927c9f6666", "Simone", "Inzaghi", "Inter"));
        add(new User("b050fd8a-1d60-4b49-9a88-05927c9f7777" ,"Massimiliano", "Allegri", "Juventus"));
        add(new User("b050fd8a-1d60-4b49-9a88-05927c9f8888", "Rudi", "Garcia", "Napoli"));
        add(new User("b050fd8a-1d60-4b49-9a88-05927c9f9999", "Edin", "Terzic", "Borusija Dortmund"));
    }};

    // TODO: set it to UserId type
    private final String userId;
    private final String name;
    private final String lastName;
    private final String teamName;

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return this.name + " " + this.lastName;
    }
}
