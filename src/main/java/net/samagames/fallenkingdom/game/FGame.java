package net.samagames.fallenkingdom.game;

import net.samagames.api.SamaGamesAPI;
import net.samagames.api.games.Game;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
public class FGame extends Game<FPlayer> implements Listener{

    private FStage step = FStage.INIT;
    private HashMap<FTeamType, FTeam> teams = new HashMap<>();
    private HashMap<Player, FPlayer> players = new HashMap<>();
    private long timerSecs;
    private int timerTaskId;

    public FGame() {
        super("fallenkingdom", "FallenKingdom", "Des royaumes qui tombent", FPlayer.class);
    }

    @Override
    public void startGame()
    {
        super.startGame();
        init();
    }

    private void init()
    {
        teams.put(FTeamType.ROUGE, new FTeam(FTeamType.ROUGE));
        teams.put(FTeamType.VERT, new FTeam(FTeamType.VERT));
        teams.put(FTeamType.BLEU, new FTeam(FTeamType.BLEU));
        teams.put(FTeamType.JAUNE, new FTeam(FTeamType.JAUNE));
        teams.put(FTeamType.ORANGE, new FTeam(FTeamType.ORANGE));
        stepPlus2(30);
        //TODO
    }

    /*private void stepPlus(long time)
    {
        step = FStage.values()[step.ordinal() + 1];
        getServer().getScheduler().runTaskLater(SamaGamesAPI.get().getPlugin(), new Runnable() {
            @Override
            public void run() {
                if(step == FStage.MATCHMAKING)
                {
                    startPlay();
                    stepPlus(12000);
                }
                else if (step == FStage.PREP)
                {
                    startPvp();
                    stepPlus(6000);
                }
                else
                {
                    step = FStage.BATTLE;
                    startBattle();
                }
            }
        }, time);
    }*/

    private void stepPlus2(long seconds)
    {
        timerSecs = seconds;
        timerTaskId = getServer().getScheduler().scheduleSyncRepeatingTask(SamaGamesAPI.get().getPlugin(), new Runnable() {
            @Override
            public void run() {
                if(timerSecs < 0)
                {
                    if(step == FStage.MATCHMAKING)
                    {
                        startPlay();
                        stepPlus2(600);
                    }
                    else if (step == FStage.PREP)
                    {
                        startPvp();
                        stepPlus2(300);
                    }
                    else
                    {
                        step = FStage.BATTLE;
                        startBattle();
                    }
                }
                else
                {
                    timerSecs--;
                    //TODO update scoreboard
                }
            }
        }, 0L, 20L);
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

    @Override
    public void handleReconnect(Player p)
    {

    }

    private void startPlay()
    {
        for(FPlayer fp : players.values())
        {
            fp.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
        }
        //TODO teleport players to team spawns
    }

    private void startPvp()
    {
        for(FPlayer fp : players.values())
        {
            fp.getPlayer().removePotionEffect(PotionEffectType.SPEED);
        }
    }

    private void startBattle()
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
        switch (step) {
            case MATCHMAKING:
                e.setCancelled(true);
                break;
            default:
                if(getBlockZone(e.getPlayer(), e.getBlock()) == FZone.ENEMY)
                    e.setCancelled(true);
                break;
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e)
    {
        if(e.getBlock().getType() == Material.OBSIDIAN)
            e.setCancelled(true); //A DISCUTER
        switch (step) {
            case MATCHMAKING:
                e.setCancelled(true);
                break;
            case PREP:
            case PVP:
                if(e.getBlock().getType() == Material.TNT || getBlockZone(e.getPlayer(), e.getBlock()) != FZone.BASE)
                    e.setCancelled(true);
                break;
            case BATTLE:
                FZone zone = getBlockZone(e.getPlayer(), e.getBlock());
                if(zone == FZone.ENEMY || e.getBlock().getType() == Material.TNT && zone == FZone.BASE)
                    e.setCancelled(true);
                break;
        }
    }

    public void setPlayerTeam(Player p, FTeamType type)
    {
        teams.get(type).addPlayer(players.get(p));
    }

    public boolean isInPlayerTeamBase(Player p, Location loc)
    {
        return players.get(p).getTeam().isInBase(loc);
    }

    private FZone getBlockZone(Player p, Block blk)
    {
        if(players.get(p).getTeam().isInBase(blk.getLocation()))
            return FZone.BASE;
        else
        {
            for(FTeam ft : teams.values())
            {
                if(ft.isInBase(blk.getLocation()))
                    return FZone.ENEMY;
            }
            return FZone.WILD;
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e)
    {
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            FPlayer damager = players.get(e.getDamager());
            FPlayer player = players.get(e.getEntity());
            if (damager.getTeam() == player.getTeam()) {
                e.setCancelled(true);
                return;
            }
            switch (step) {
                case INIT:
                case PREP:
                    e.setCancelled(true);
                    break;
                case PVP:
                case BATTLE:
                    if(e.getDamage() > player.getHealth())
                        player.getTeam().addKillCoins(player);
                    break;
            }
        }

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e)
    {
        e.setKeepInventory(true);
        e.setKeepLevel(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e)
    {
        if(getBlockZone(e.getPlayer(), e.getClickedBlock()) == FZone.ENEMY)
            e.setCancelled(true);
    }
}
