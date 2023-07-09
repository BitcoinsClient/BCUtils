package de.bitcoinclient.bcutils;

import de.bitcoinclient.bcutils.gui.result.GuiMenu;
import de.bitcoinclient.bcutils.log.Logger;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class BCUtils extends JavaPlugin {
    private static Logger logger;
    private static BCUtils bcUtils;
    public static final HashMap<Player, GuiMenu> OPEN_MENUS = new HashMap<>();

    @Override
    public void onEnable() {
        bcUtils = this;
        logger = new Logger();
        getPluginLogger().sendMessage(" ");
        getPluginLogger().sendInfo("Das Plugin wurde aktiviert!");
        getPluginLogger().sendInfo("Version: " + this.getDescription().getVersion());
        getPluginLogger().sendInfo("Author: " + this.getDescription().getAuthors());
        getPluginLogger().sendMessage(" ");
    }

    @Override
    public void onDisable() {
        getPluginLogger().sendMessage(" ");
        getPluginLogger().sendInfo("Das Plugin wurde deaktiviert!");
        getPluginLogger().sendMessage(" ");
    }


    public static Logger getPluginLogger() {
        return logger;
    }

    public static BCUtils getBCUtils() {
        return bcUtils;
    }
}
