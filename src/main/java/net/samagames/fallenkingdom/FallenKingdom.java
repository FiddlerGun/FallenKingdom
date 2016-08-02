package net.samagames.fallenkingdom;

import net.samagames.api.SamaGamesAPI;
import net.samagames.fallenkingdom.game.FGame;
import org.bukkit.craftbukkit.v1_9_R2.Overridden;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * ╱╲＿＿＿＿＿＿╱╲
 * ▏╭━━╮╭━━╮▕
 * ▏┃＿＿┃┃＿＿┃▕
 * ▏┃＿▉┃┃▉＿┃▕
 * ▏╰━━╯╰━━╯▕
 * ╲╰╰╯╲╱╰╯╯╱  Created by Silvanosky on 04/07/2016
 * ╱╰╯╰╯╰╯╰╯╲
 * ▏▕╰╯╰╯╰╯▏▕
 * ▏▕╯╰╯╰╯╰▏▕
 * ╲╱╲╯╰╯╰╱╲╱
 * ＿＿╱▕▔▔▏╲＿＿
 * ＿＿▔▔＿＿▔▔＿＿
 */
public class FallenKingdom extends JavaPlugin{

    private ArrayList<FKTeam> teams;
    private int maxPlayers = 4;

    private FGame game;

    public void onEnable()
    {
        game = new FGame();
        SamaGamesAPI.get().getGameManager().registerGame(game);

        PluginDescriptionFile pdffile = this.getDescription();
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents((Listener) this, this);
        init();
        this.getLogger().info(pdffile.getName() + " activated.");
    }

    @Overridden
    public void onDisable()
    {
        PluginDescriptionFile pdffile = this.getDescription();
        this.getLogger().info(pdffile.getName() + " désactivé.");
    }

    private void init()
    {
        teams.add(new FKTeam(maxPlayers, "Rouge"));
        teams.add(new FKTeam(maxPlayers, "Vert"));
        teams.add(new FKTeam(maxPlayers, "Rouge"));
        teams.add(new FKTeam(maxPlayers, "Bleu"));
        teams.add(new FKTeam(maxPlayers, "Jaune"));
    }

}

