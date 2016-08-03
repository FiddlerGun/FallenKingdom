package net.samagames.fallenkingdom.game;

import net.samagames.api.SamaGamesAPI;
import net.samagames.api.games.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import java.util.HashMap;
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

    private int step = 0;
    private boolean canBreak;
    private HashMap<FTeamType, FTeam> teams;
    private HashMap<Player, FPlayer> players;

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
        teams.put(FTeamType.ROUGE, new FTeam(FTeamType.ROUGE));
        teams.put(FTeamType.VERT, new FTeam(FTeamType.VERT));
        teams.put(FTeamType.BLEU, new FTeam(FTeamType.BLEU));
        teams.put(FTeamType.JAUNE, new FTeam(FTeamType.JAUNE));
        teams.put(FTeamType.ORANGE, new FTeam(FTeamType.ORANGE));
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
        super.handleLogin(p);
        players.put(p, new FPlayer(p));
        //TODO teleport player to lobby spawn
    }

    @Override
    public void handleLogout(Player p)
    {
        super.handleLogout(p);
        //TODO
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

    public void endGame()
    {
        //TODO stop tasks, rewards, etc.
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

    public void setPlayerTeam(Player p, FTeamType type)
    {
        teams.get(type).addPlayer(players.get(p));
    }
}
