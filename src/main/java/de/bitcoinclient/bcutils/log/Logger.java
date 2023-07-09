package de.bitcoinclient.bcutils.log;

import org.bukkit.Bukkit;

public class Logger {
    public Logger() {

    }

    public void sendDebug(String message) {
        Bukkit.getConsoleSender().sendMessage("DEBUG | " + message);
    }

    public void sendInfo(String message) {
        Bukkit.getConsoleSender().sendMessage("Info | "+message);
    }

    public void sendError(String message) {
        Bukkit.getConsoleSender().sendMessage("ERROR | "+message);
    }

    public void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
}
