package com.nordeus.jobfair.auctionservice.auctionservice.domain.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class Player {

    // Hard-coded the list of players instead of database
    // We could also generate players in AuctionServiceImpl, but we will pretend that we have database with players
    public static Collection<Player> players = new ArrayList<>() {{
        add(new Player("Lionel", "Messi", "Inter Miami", 96));
        add(new Player("Erling", "Haaland", "Man City", 95));
        add(new Player("Kylian", "Mbappe", "PSG", 93));
        add(new Player("Kevin", "De Bruyne", "Man City", 92));
        add(new Player("Julian", "Alvarez", "Man City", 91));
        add(new Player("Victor", "Osimhen", "Napoli", 91));
        add(new Player("Luka", "Modric", "Real Madrid", 90));
        add(new Player("Mohamed", "Salah", "Liverpool", 90));
        add(new Player("Robert", "Lewandowski", "Barcelona", 89));
        add(new Player("Yassine", "Bounou", "Al-Hilal", 88));
        add(new Player("Ilkay", "Gundogan", "Barcelona", 87));
        add(new Player("Emiliano", "Martinez", "Aston Villa", 87));
        add(new Player("Karim", "Benzema", "Al-Ittihad", 87));
        add(new Player("Kvicha", "Kvaratskhelia", "Napoli", 87));
        add(new Player("Jude", "Bellingham", "Real Madrid", 87));
        add(new Player("Harry", "Kane", "Bayern Munich", 87));
        add(new Player("Lautaro", "Martinez", "Inter Milan", 86));
        add(new Player("Antoine", "Griezmann", "Atletico Madrid", 86));
        add(new Player("Kim", " Min-Jae", "Bayern Munich", 85));
        add(new Player("Andre", "Onana", "Man United", 85));
        add(new Player("Bukayo", "Saka", "Arsenal", 85));
        add(new Player("Josko", "Gvardiol", "Man City", 85));
        add(new Player("Jamal", "Musiala", "Bayern Munich", 84));
        add(new Player("Nicolo", "Barella", "Inter Milan", 84));
        add(new Player("Martin", "Odegaard", "Arsenal", 84));
    }};

    private final String name;
    private final String lastName;
    private final String clubName;
    private final int rating;
    private boolean forAuction;

    public Player(String name, String lastName, String clubName, int rating) {
        this.name = name;
        this.lastName = lastName;
        this.clubName = clubName;
        this.rating = rating;
        this.forAuction = true;
    }

    @Override
    public String toString() {
        return this.name + " " + this.lastName + ", " + this.clubName + ". Rating: " + this.rating;
    }

    public boolean isForAuction() {
        return forAuction;
    }

    public void setForAuction(boolean forAuction) {
        this.forAuction = forAuction;
    }
}
