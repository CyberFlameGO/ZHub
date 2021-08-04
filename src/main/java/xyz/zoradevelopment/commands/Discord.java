package xyz.zoradevelopment.commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.zoradevelopment.Hub;
import xyz.zoradevelopment.utils.CC;

import java.util.UUID;

public class Discord implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        player.sendMessage(CC.color(Hub.links.getConfiguration().getString("Discord")));
        return false;
    }
}
