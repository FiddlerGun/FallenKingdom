package net.samagames.fallenkingdom.game;

import net.samagames.api.games.GamePlayer;
import org.bukkit.entity.Player;

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
public class FPlayer extends GamePlayer {

    private FTeam team;
    private Player player;
    public Integer coins = 300;

    public FPlayer(Player player) {
        super(player);
    }

    public void setTeam(FTeam team)
    {
        this.team = team;
    }

    public FTeam getTeam()
    {return team;}

    public double getHealth()
    {
        return player.getHealth();
    }

    public void addCoins(int c)
    {
        coins += c;
    }

    public Player getPlayer()
    {return player;}
}
