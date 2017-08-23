package net.samagames.fallenkingdom.game;

import net.samagames.api.SamaGamesAPI;
import net.samagames.tools.Area;
import org.bukkit.Location;

import java.util.ArrayList;

/*
 * This file is part of FallenKingdom.
 *
 * FallenKingdom is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FallenKingdom is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FallenKingdom.  If not, see <http://www.gnu.org/licenses/>.
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
