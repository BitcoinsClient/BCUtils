package de.bitcoinclient.bcutils.messages;

import de.bitcoinclient.bcutils.config.ConfigManager;
import de.bitcoinclient.bcutils.log.Logger;
import org.bukkit.entity.Player;

import java.io.IOException;

public class MessageUtil {
    private final Logger logger;
    public MessageUtil(Logger logger) {
        this.logger = logger;
    }

    public void addID(LanguageType languageType, String id, String message, boolean isForced) {
        getConfig(languageType).add2Config(id,message,isForced);
    }

    public String getMessage(LanguageType languageType, String id) {
        ConfigManager configManager = getConfig(languageType);
        if(!configManager.hasInConfig(id)) {
            return id;
        }
        return configManager.getValueAsString(id).replace("&","§");
    }

    public String getMessage(String languageKey, String id) {
        LanguageType languageType = LanguageType.valueOf(languageKey.toUpperCase());
        ConfigManager configManager = getConfig(languageType);
        if(!configManager.hasInConfig(id)) {
            return id;
        }
        return configManager.getValueAsString(id).replace("&","§");
    }

    public String getMessage(Player player, String id) {
        LanguageType languageType = LanguageType.valueOf(player.getLocale().toUpperCase());
        ConfigManager configManager = getConfig(languageType);
        if(!configManager.hasInConfig(id)) {
            return id;
        }
        return configManager.getValueAsString(id).replace("&","§");
    }

    public String getMessage(LanguageType languageType, String id, boolean replaceAnd) {
        ConfigManager configManager = getConfig(languageType);
        if(!configManager.hasInConfig(id)) {
            return id;
        }
        if(replaceAnd) {
            return configManager.getValueAsString(id).replace("&","§");
        }
        return configManager.getValueAsString(id);
    }

    public String getMessage(String languageKey, String id, boolean replaceAnd) {
        LanguageType languageType = LanguageType.valueOf(languageKey.toUpperCase());
        ConfigManager configManager = getConfig(languageType);
        if(!configManager.hasInConfig(id)) {
            return id;
        }
        if(replaceAnd) {
            return configManager.getValueAsString(id).replace("&","§");
        }
        return configManager.getValueAsString(id);
    }

    public String getMessage(Player player, String id, boolean replaceAnd) {
        LanguageType languageType = LanguageType.valueOf(player.getLocale().toUpperCase());
        ConfigManager configManager = getConfig(languageType);
        if(!configManager.hasInConfig(id)) {
            return id;
        }
        if(replaceAnd) {
            return configManager.getValueAsString(id).replace("&","§");
        }
        return configManager.getValueAsString(id);
    }

    public void sendPlayer(Player player, String id) {
        player.sendMessage(getMessage(player,id));
    }

    public void sendPlayer(Player player, String prefix, String id) {
        player.sendMessage(prefix+getMessage(player,id));
    }

    public ConfigManager getConfig(LanguageType languageType) {
        ConfigManager configManager;
        try {
            configManager = new ConfigManager(languageType.getKey());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return configManager;
    }
}
