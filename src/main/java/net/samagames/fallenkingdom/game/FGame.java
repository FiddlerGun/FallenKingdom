package net.samagames.fallenkingdom.game;

import net.samagames.api.SamaGamesAPI;
import net.samagames.api.games.Game;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;


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
public class FGame extends Game<FPlayer> {

    int maxPlayers = 4;
    int step = 0;
    private ArrayList<FTeam> teams;

    public FGame() {
        super("fallenkingdom", "FallenKingdom", "Des royaumes qui tombent", FPlayer.class);
        init();
    }

    @Override
    public void startGame()
    {
        super.startGame();
    }

    private void init()
    {
        teams.add(new FTeam(maxPlayers, "Rouge"));
        teams.add(new FTeam(maxPlayers, "Vert"));
        teams.add(new FTeam(maxPlayers, "Jaune"));
        teams.add(new FTeam(maxPlayers, "Bleu"));
        teams.add(new FTeam(maxPlayers, "Orange"));
        stepPlus(10);
    }

    public void stepPlus(long minutes)
    {
        step++;
        getServer().getScheduler().runTaskLater(SamaGamesAPI.get().getPlugin(), new Runnable() {
            @Override
            public void run() {
                if (step == 1)
                {
                    startPvp();
                    stepPlus(5);
                }
                else
                    startBattle();
            }
        }, 1200l * minutes);
    }

    public void handleLogin(Player p)
    {
        //TODO
    }

    public void handleLogout(Player p)
    {
        //TODO
    }

    public void handleReconnect(Player p)
    {
        //TODO
    }

    public void endGame()
    {
        //TODO
    }

    public void startPvp()
    {
        //TODO
    }

    public void startBattle()
    {
        //TODO
    }
}
