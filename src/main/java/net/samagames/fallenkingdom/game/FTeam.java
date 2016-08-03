package net.samagames.fallenkingdom.game;

import java.util.ArrayList;

/**
 * Created by Laurent on 02/08/2016.
 */
public class FTeam {

    private int size;
    public FTeamType type;
    ArrayList<FPlayer> players;

    public FTeam(FTeamType type)
    {
        this.type = type;
    }

    public void addPlayer(FPlayer p)
    {
        players.add(p);
        p.setTeam(this);
    }

    public void removePlayer(FPlayer p)
    {
        players.remove(p);
        p.setTeam(null);
    }

    public int count()
    {
        return players.size();
    }
}
