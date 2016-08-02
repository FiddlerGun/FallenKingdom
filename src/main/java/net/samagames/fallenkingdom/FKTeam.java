package net.samagames.fallenkingdom;

import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Aluxima on 05/07/2016.
 */
public class FKTeam {

    private int size;
    ArrayList<Player> players;

    public FKTeam(int size, String name)
    {
        this.size = size;
    }

    public void addPlayer(Player p)
    {
        if(players.size() < this.size)
            players.add(p);
    }

    public void removePlayer(Player p)
    {
        players.remove(p);
    }

}
