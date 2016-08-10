package net.samagames.fallenkingdom.game;

import net.samagames.api.games.GamePlayer;
import org.bukkit.entity.Player;

/**
 * ╱╲＿＿＿＿＿＿╱╲
 * ▏╭━━╮╭━━╮▕
 * ▏┃＿＿┃┃＿＿┃▕
 * ▏┃＿▉┃┃▉＿┃▕
 * ▏╰━━╯╰━━╯▕
 * ╲╰╰╯╲╱╰╯╯╱  Created by Silvanosky on 02/08/2016
 * ╱╰╯╰╯╰╯╰╯╲
 * ▏▕╰╯╰╯╰╯▏▕
 * ▏▕╯╰╯╰╯╰▏▕
 * ╲╱╲╯╰╯╰╱╲╱
 * ＿＿╱▕▔▔▏╲＿＿
 * ＿＿▔▔＿＿▔▔＿＿
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
