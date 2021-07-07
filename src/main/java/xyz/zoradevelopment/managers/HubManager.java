package xyz.zoradevelopment.managers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.zoradevelopment.Hub;
import xyz.zoradevelopment.utils.CC;

import java.util.ArrayList;

public class HubManager {

    public void selector(Player player) {
        int slot = Hub.configuration.getConfiguration().getInt("Selector.Slot");
        final boolean enabled = Hub.configuration.getConfiguration().getBoolean("Selector.Enabled");
        ItemStack selector = new ItemStack(Material.valueOf(Hub.configuration.getConfiguration().getString("Selector.Item")));
        ItemMeta selectormeta = selector.getItemMeta();
        selectormeta.setDisplayName(CC.color(Hub.configuration.getConfiguration().getString("Selector.Name")));
        ArrayList<String> lore = new ArrayList<>();
        for (final String selectorlore : Hub.configuration.getConfiguration().getStringList("Selector.Lore")) {
            lore.add(CC.color(selectorlore));
        }
        selectormeta.setLore(lore);
        selector.setItemMeta(selectormeta);
        if (enabled) {
            player.getInventory().setItem(slot, selector);
        }
    }

    public void enderpearl(Player player) {
        int slot = Hub.configuration.getConfiguration().getInt("EnderButt.Slot");
        final boolean enabled = Hub.configuration.getConfiguration().getBoolean("EnderButt.Enabled");
        ItemStack enderpearl = new ItemStack(Material.valueOf(Hub.configuration.getConfiguration().getString("EnderButt.Item")));
        ItemMeta enderpearlmeta = enderpearl.getItemMeta();
        enderpearlmeta.setDisplayName(CC.color(Hub.configuration.getConfiguration().getString("EnderButt.Name")));
        ArrayList<String> lore = new ArrayList<>();
        for (final String enderbuttlore : Hub.configuration.getConfiguration().getStringList("EnderButt.Lore")) {
            lore.add(CC.color(enderbuttlore));
        }
        enderpearlmeta.setLore(lore);
        enderpearl.setItemMeta(enderpearlmeta);
        if (enabled) {
            player.getInventory().setItem(slot, enderpearl);
        }
    }

    public void hub(Player player) {
        int slot = Hub.configuration.getConfiguration().getInt("Hub.Slot");
        final boolean enabled = Hub.configuration.getConfiguration().getBoolean("Hub.Enabled");
        ItemStack hub = new ItemStack(Material.valueOf(Hub.configuration.getConfiguration().getString("Hub.Item")));
        ItemMeta hubmeta = hub.getItemMeta();
        hubmeta.setDisplayName(CC.color(Hub.configuration.getConfiguration().getString("Hub.Name")));
        ArrayList<String> lore = new ArrayList<>();
        for (final String hublore : Hub.configuration.getConfiguration().getStringList("Hub.Lore")) {
            lore.add(CC.color(hublore));
        }
        hubmeta.setLore(lore);
        hub.setItemMeta(hubmeta);
        if (enabled) {
            player.getInventory().setItem(slot, hub);
        }
    }
}
