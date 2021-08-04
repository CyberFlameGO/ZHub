package xyz.zoradevelopment.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.zoradevelopment.utils.CC;

import java.util.Queue;

public class ZoraHub implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage(CC.color("&f&m----------------------------------------------"));
        player.sendMessage(CC.color("&aZora Hub &7has been made by &aZora Development"));
        player.sendMessage(CC.color("&aServer is using version &71.0"));
        player.sendMessage("");
        player.sendMessage("https://www.mc-market.org/resources/20586/");
        player.sendMessage(CC.color("&f&m----------------------------------------------"));

        return false;
    }
}
