package xyz.zoradevelopment.developer;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.zoradevelopment.utils.CC;

public class Join implements Listener {

    @EventHandler
    public void onDevJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (player.getName().equals("michasmik")) {
            player.sendMessage("");
            player.sendMessage(CC.color("&a[ZORA]"));
            player.sendMessage("");
            player.sendMessage(CC.color("&7&oThis server is using your Hub Core."));
            player.sendMessage("");
        }
    }
}
