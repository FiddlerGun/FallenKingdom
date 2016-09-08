package net.samagames.fallenkingdom.game;

import net.samagames.api.SamaGamesAPI;
import net.samagames.tools.Area;
import org.bukkit.Location;

import java.util.ArrayList;

/**
 * Created by Laurent on 02/08/2016.
 */
public class FTeam {

    private int size;
    public FTeamType type;
    private ArrayList<FPlayer> players = new ArrayList<>();
    private Area baseArea;
    private Location baseSpawn;

    public FTeam(FTeamType type)
    {
        this.type = type;
        baseArea = net.samagames.tools.Area.str2area(SamaGamesAPI.get().getGameManager().getGameProperties().getConfigs().get(type.name()).getAsString());
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

    public double getHealth()
    {
        double total = 0;
        for(FPlayer p : players)
            total += p.getHealth();
        return total;
    }

    public Area getBaseArea()
    {
        return baseArea;
    }

    public boolean isInBase(Location loc)
    {
        return baseArea.isInArea(loc);
    }

    public void addKillCoins(FPlayer p)
    {
        for(FPlayer fp : players)
            fp.addCoins(50);
        p.addCoins(950);
    }

    public void tpPlayersToBase()
    {
        for(FPlayer fp : players)
        {
            fp.getPlayer().teleport(baseSpawn);
        }
    }
}
