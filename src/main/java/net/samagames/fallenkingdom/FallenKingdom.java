package net.samagames.fallenkingdom;

import net.samagames.api.SamaGamesAPI;
import net.samagames.fallenkingdom.game.FGame;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


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

