package net.samagames.fallenkingdom.game;

import net.samagames.api.SamaGamesAPI;
import net.samagames.api.games.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

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

    private int maxPlayers = 4;
    private int step = 0;
    private boolean canBreak;
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
        stepPlus(400);
        canBreak = false;
        //TODO
    }

    public void stepPlus(long time)
    {
        step++;
        getServer().getScheduler().runTaskLater(SamaGamesAPI.get().getPlugin(), new Runnable() {
            @Override
            public void run() {
                if(step == 1)
                {
                    startPlay();
                    stepPlus(12000);
                }
                else if (step == 2)
                {
                    startPvp();
                    stepPlus(6000);
                }
                else
                    startBattle();
            }
        }, time);
    }

    @Override
    public void handleLogin(Player p)
    {
        //TODO teleport player to lobby spawn
        super.handleLogin(p);
    }

    @Override
    public void handleLogout(Player p)
    {
        //TODO
        super.handleLogout(p);
    }

    public void endGame()
    {
        //TODO stop tasks, rewards, etc.
    }

    public void startPlay()
    {
        canBreak = true;
        //TODO teleport players to team spawns
    }

    public void startPvp()
    {
        //TODO enable PvP
    }

    public void startBattle()
    {
        //TODO
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        if(!canBreak)
            e.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e)
    {
        if(!canBreak)
            e.setCancelled(true);
    }
}
