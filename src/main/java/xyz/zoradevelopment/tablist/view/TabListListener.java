package xyz.zoradevelopment.tablist.view;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.zoradevelopment.Hub;
import xyz.zoradevelopment.tablist.TablistEntrySupplier;
import xyz.zoradevelopment.utils.CC;

public class TabListListener implements TablistEntrySupplier {

    @Override
    public Table<Integer, Integer, String> getEntries(Player player) {
        Table<Integer, Integer, String> table = HashBasedTable.create();
        // The first integer is the 'X' and the second integer is the 'Y'.
        int online = Bukkit.getOnlinePlayers().size();
        String players = String.valueOf(online);
        table.put(0, 0, CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.0")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 1, CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.1")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 2, CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.2")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 3, CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.3")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 4, CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.4")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 5, CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.5")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 6, CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.6")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 7, CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.7")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 8, CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.8")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 9,CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.9")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 10, CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.10")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 11, CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.11")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 12, CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.12")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 13, CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.13")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 14, CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.14")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 15, CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.15")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 16,CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.16")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 17, CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.17")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 18, CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.18")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(0, 19, CC.color(Hub.configuration.getConfiguration().getString("TabList.Left.19")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));


        table.put(1, 0, CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.0")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 1, CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.1")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 2, CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.2")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 3, CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.3")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 4, CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.4")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 5, CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.5")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 6, CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.6")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 7, CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.7")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 8, CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.8")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 9,CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.9")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 10, CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.10")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 11, CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.11")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 12, CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.12")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 13, CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.13")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 14, CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.14")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 15, CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.15")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 16,CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.16")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 17, CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.17")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 18, CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.18")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(1, 19, CC.color(Hub.configuration.getConfiguration().getString("TabList.Middle.19")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));

        table.put(2, 0, CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.0")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 1, CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.1")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 2, CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.2")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 3, CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.3")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 4, CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.4")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 5, CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.5")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 6, CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.6")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 7, CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.7")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 8, CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.8")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 9,CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.9")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 10, CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.10")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 11, CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.11")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 12, CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.12")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 13, CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.13")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 14, CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.14")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 15, CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.15")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 16,CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.16")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 17, CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.17")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 18, CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.18")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));
        table.put(2, 19, CC.color(Hub.configuration.getConfiguration().getString("TabList.Right.19")).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))));


        return table;
    }
}
