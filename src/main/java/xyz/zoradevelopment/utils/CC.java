package xyz.zoradevelopment.utils;

import org.bukkit.ChatColor;

public class CC {

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
