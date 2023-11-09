package com.nordeus.jobfair.auctionservice.auctionservice.domain.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class Player {

    // Hard-coded the list of players
    public static Collection<Player> players = new ArrayList<Player>(){{
        add(new Player("Lionel", "Messi", "ST", "Playmaker", "Inter Miami", 96));
        add(new Player("Erling", "Haaland", "ST", "One-on-One Scorer", "Man City", 95));
        add(new Player("Kylian", "Mbappe", "ST", "One-on-One Scorer", "PSG", 93));
        add(new Player("Kevin", "De Bruyne", "AMC", "Playmaker", "Man City", 92));
        add(new Player("Julian", "Alvarez", "AMC", "Shadow Striker", "Man City", 91));
        add(new Player("Victor", "Osimhen", "ST", "One-on-One Scorer", "Napoli", 91));
        add(new Player("Luka", "Modric", "AMC", "Playmaker", "Real Madrid", 90));
        add(new Player("Mohamed", "Salah", "AMR", "Shadow Striker", "Liverpool", 90));
        add(new Player("Robert", "Lewandowski", "ST", "One-on-One Scorer", "Barcelona", 89));
        add(new Player("Yassine", "Bounou", "GK", "One-on-One Stopper", "Al-Hilal", 88));
        add(new Player("Ilkay", "Gundogan", "MC", "Playmaker", "Barcelona", 87));
        add(new Player("Emiliano", "Martinez", "GK", "Penalty-kick Stopper", "Aston Villa", 87));
        add(new Player("Karim", "Benzema", "ST", "One-on-One Scorer", "Al-Ittihad", 87));
        add(new Player("Kvicha", "Kvaratskhelia", "AML", "One-on-One Scorer", "Napoli", 87));
        add(new Player("Jude", "Bellingham", "MC", "Playmaker", "Real Madrid", 87));
        add(new Player("Harry", "Kane", "ST", "One-on-One Scorer", "Bayern Munich", 87));
        add(new Player("Lautaro", "Martinez", "ST", "One-on-One Scorer", "Inter Milan", 86));
        add(new Player("Antoine", "Griezmann", "ST", "Free-kick Specialist", "Atletico Madrid", 86));
        add(new Player("Kim"," Min-Jae", "DC", "Defensive Wall", "Bayern Munich", 85));
        add(new Player("Andre", "Onana", "GK", "One-on-One Stopper", "Man United", 85));
        add(new Player("Bukayo", "Saka", "AML", "Shadow Striker", "Arsenal", 85));
        add(new Player("Josko", "Gvardiol", "DC", "Aerial Defender", "Man City", 85));
        add(new Player("Jamal","Musiala", "AMC", "Shadow Striker", "Bayern Munich", 84));
        add(new Player("Nicolo", "Barella", "MC", "Playmaker", "Inter Milan", 84));
        add(new Player("Martin", "Odegaard", "AMC", "Shadow Striker", "Arsenal", 84));
    }};

    private final String name;
    private final String lastName;
    private final String clubName;
    private final String position;
    private final String specialAbility;
    private final int rating;
    private boolean onAuction;

    public Player(String name, String lastName, String position, String specialAbility, String clubName, int rating) {
        this.name = name;
        this.lastName = lastName;
        this.clubName = clubName;
        this.position = position;
        this.specialAbility = specialAbility;
        this.rating = rating;
        this.onAuction = false;
    }

    @Override
    public String toString() {
        return this.name + " " + this.lastName + " " + this.rating + ", " + this.clubName + ". Position: " + this.position + "- Special Ability: " + this.specialAbility;
    }

    public boolean isOnAuction() {
        return onAuction;
    }

    public void setOnAuction(boolean onAuction) {
        this.onAuction = onAuction;
    }
}
