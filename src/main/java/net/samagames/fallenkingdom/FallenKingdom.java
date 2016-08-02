package net.samagames.fallenkingdom;

import net.samagames.api.SamaGamesAPI;
import net.samagames.fallenkingdom.game.FGame;
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

    public void onEnable()
    {
        game = new FGame();
        SamaGamesAPI.get().getGameManager().registerGame(game);
    }

    public void onDisable()
    {
        //TODO
    }
}

