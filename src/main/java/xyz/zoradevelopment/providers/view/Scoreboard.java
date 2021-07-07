package xyz.zoradevelopment.providers.view;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.zoradevelopment.Hub;
import xyz.zoradevelopment.providers.ScoreboardEntrySupplier;
import xyz.zoradevelopment.utils.CC;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard implements ScoreboardEntrySupplier {
    @Override
    public String getScoreboardTitle() {
        return CC.color(Hub.lang.getConfiguration().getString("Scoreboard.Title"));
    }

    @Override
    public List<String> getScoreboardEntries(Player player) {
        List<String> lines = new ArrayList<>();
        int onlineplayers = Bukkit.getOnlinePlayers().size();
        String players = String.valueOf(onlineplayers);
        for (final String scoreboard : Hub.lang.getConfiguration().getStringList("Scoreboard.Lines")) {
            lines.add(CC.color(scoreboard).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        }
        return lines;
    }
}
