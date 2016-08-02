package net.samagames.fallenkingdom.game;

import java.util.ArrayList;

/**
 * Created by Laurent on 02/08/2016.
 */
public class FTeam {

    private int size;
    ArrayList<FPlayer> players;

    public FTeam(int size, String name)
    {
        this.size = size;
    }

    public void addPlayer(FPlayer p)
    {
        players.add(p);
    }

    public void removePlayer(FPlayer p)
    {
        players.remove(p);
    }

    public int count()
    {
        return players.size();
    }
}
