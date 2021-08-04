package xyz.zoradevelopment.providers.view;

import me.joeleoli.portal.shared.queue.Queue;
import me.signatured.ezqueuespigot.EzQueueAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import xyz.zoradevelopment.Hub;
import xyz.zoradevelopment.providers.ScoreboardEntrySupplier;
import xyz.zoradevelopment.utils.CC;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard implements ScoreboardEntrySupplier {

    private String queueSystem = Hub.settings.getConfiguration().getString("Queue");

    @Override
    public String getScoreboardTitle() {
        return CC.color(Hub.lang.getConfiguration().getString("Scoreboard.Title"));
    }


    @Override
    public List<String> getScoreboardEntries(Player player) {
        List<String> lines = new ArrayList<>();
        List<String> queueLines = new ArrayList<>();
        List<String> ezQueue = new ArrayList<>();

        // EZQUEUE

        int onlineplayers = Bukkit.getOnlinePlayers().size();
        int position = EzQueueAPI.getPosition(player);
        String playerPosition = String.valueOf(position);
        int size = EzQueueAPI.getQueueSize(EzQueueAPI.getQueue(player));
        String queueSize = String.valueOf(size);
        this.queueSystem = queueSystem;

        int globalPlayers = Hub.getInstance().getOnlineCount("ALL");
        String global = String.valueOf(globalPlayers);

        for (final String scoreboard : Hub.lang.getConfiguration().getStringList("Scoreboard.Normal")) {
            lines.add(CC.color(scoreboard).replace("<global>", global).replace("<player>", player.getName()).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        }
        if (queueSystem.equalsIgnoreCase("EzQueue")) {
            if (EzQueueAPI.getQueue(player) != null) {
                lines.clear();
                for (final String queueScoreboard : Hub.lang.getConfiguration().getStringList("Scoreboard.EzQueue")) {
                    lines.add(CC.color(queueScoreboard).replace("<global>", global).replace("<player>", player.getName()).replace("<position>", playerPosition).replace("<size>", queueSize).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
                }
            }
        } else if (queueSystem.equalsIgnoreCase("NONE")) {
            lines.clear();
            for (final String scoreboard : Hub.lang.getConfiguration().getStringList("Scoreboard.Normal")) {
                lines.add(CC.color(scoreboard).replace("<global>", global).replace("<player>", player.getName()).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
            }
        } else if (queueSystem.equalsIgnoreCase("Portal")) {
            lines.clear();
            for (final String portalScoreboard : Hub.lang.getConfiguration().getStringList("Scoreboard.Portal")) {
                lines.add(CC.color(portalScoreboard).replace("<global>", global).replace("<player>", player.getName()).replace("<position>", String.valueOf(Queue.getByPlayer(player.getUniqueId()).getPosition(player.getUniqueId()))).replace("<size>", String.valueOf(Queue.getByPlayer(player.getUniqueId()).getPlayers().size())).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
            }
        }
        return lines;
    }
}
