package xyz.zoradevelopment.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.zoradevelopment.Hub;
import xyz.zoradevelopment.utils.CC;

public class Website implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        player.sendMessage(CC.color(Hub.links.getConfiguration().getString("Website")));

        return false;
    }
}