package net.samagames.fallenkingdom;

import net.samagames.api.SamaGamesAPI;
import net.samagames.fallenkingdom.game.FGame;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


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

    private FGame game;

    @Override
    public void onEnable()
    {
        game = new FGame();
        SamaGamesAPI.get().getGameManager().registerGame(game);
        SamaGamesAPI.get().getGameManager().setMaxReconnectTime(2);
        PluginDescriptionFile pdffile = this.getDescription();
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents((Listener) this, this);
        this.getLogger().info("Fallen Kingdom activated.");
    }

    @Override
    public void onDisable()
    {
        PluginDescriptionFile pdffile = this.getDescription();
        this.getLogger().info("Fallen Kingdom deactivated.");
    }
}

