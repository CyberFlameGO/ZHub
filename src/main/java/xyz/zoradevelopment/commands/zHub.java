package xyz.zoradevelopment.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.zoradevelopment.utils.CC;

public class zHub implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage(CC.color("&f&m------------------------------------------"));
        player.sendMessage(CC.color("&azHub &7has been made by &aZora Development"));
        player.sendMessage(CC.color("&f&m------------------------------------------"));

        return false;
    }
}
