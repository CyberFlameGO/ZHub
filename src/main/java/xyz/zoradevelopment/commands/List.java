package xyz.zoradevelopment.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.zoradevelopment.Hub;
import xyz.zoradevelopment.utils.CC;

public class List implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }

        int online = Bukkit.getOnlinePlayers().size();
        String onlinePlayers = String.valueOf(online);

        Player player = (Player) sender;
        if (player.hasPermission("hub.command.list")) {
            player.sendMessage(CC.color(Hub.lang.getConfiguration().getString("List")).replace("<player>", player.getName()).replace("<online_players>", onlinePlayers));
        } else {
            CC.color("&cYou don't have permission to execute this command");
        }

        return false;
    }
}
