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

    public FPlayer(Player player) {
        super(player);
    }

    public void setTeam(FTeam team)
    {
        this.team = team;
    }

    public FTeam getTeam()
    {
        return team;
    }

}
