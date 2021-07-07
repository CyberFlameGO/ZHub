package xyz.zoradevelopment.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.zoradevelopment.Hub;
import xyz.zoradevelopment.managers.HubManager;
import xyz.zoradevelopment.utils.CC;

import java.util.ArrayList;

public class HubListener implements Listener {

    HubManager hubManager = new HubManager();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        final boolean enabled = Hub.settings.getConfiguration().getBoolean("take-damage-entity");
        if (!enabled) {
            event.setDamage(0);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage2(EntityDamageEvent event) {
        final boolean enabled = Hub.settings.getConfiguration().getBoolean("take-damage");
        if (!enabled) {
            event.setDamage(0);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBuild(BlockPlaceEvent event) {
        final boolean enabled = Hub.settings.getConfiguration().getBoolean("build-blocks");
        if (!enabled) {
            event.setCancelled(true);
        } else if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        final boolean enabled = Hub.settings.getConfiguration().getBoolean("break-blocks");
        if (!enabled) {
            event.setCancelled(true);
        } else if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onTake(PlayerPickupItemEvent event) {
        final boolean enabled = Hub.settings.getConfiguration().getBoolean("pickup-items");
        if (!enabled) {
            event.setCancelled(true);
        } else if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        final boolean enabled = Hub.settings.getConfiguration().getBoolean("drop-items");
        if (!enabled) {
            event.setCancelled(true);
        } else if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        final boolean enabled = Hub.settings.getConfiguration().getBoolean("join-message");
        if (enabled) {
            for (final String joinmessage : Hub.lang.getConfiguration().getStringList("Join.Message")) {
                player.sendMessage(CC.color(joinmessage).replace("<player>", player.getName()));
            }
        }
        player.setHealth(20);
        player.setFoodLevel(20);
        hubManager.selector(player);
        hubManager.hub(player);
        hubManager.enderpearl(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        event.getPlayer().getInventory().clear();
    }

    @EventHandler
    public void onProjectileLaunch(PlayerInteractEvent e) {
        if (e.getItem().getType() == Material.ENDER_PEARL && e.getAction() == Action.RIGHT_CLICK_AIR || e.getItem().getType() == Material.ENDER_PEARL && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(2.5F));
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
            e.getPlayer().updateInventory();
            e.setCancelled(true);
        } else {
            e.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        final boolean enabled = Hub.settings.getConfiguration().getBoolean("No-Weather");
        if (enabled) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {
        final boolean enabled = Hub.settings.getConfiguration().getBoolean("No-Food");
        if (enabled) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        final boolean enabled = Hub.settings.getConfiguration().getBoolean("Chat");
        if (!enabled) {
            event.setCancelled(true);
        } else if (event.getPlayer().hasPermission("chat.bypass")) {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        final boolean enabled = Hub.settings.getConfiguration().getBoolean("Mobs-Spawn");
        if (!enabled) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Inventory inventory = Bukkit.createInventory(player, Hub.configuration.getConfiguration().getInt("Inventory.Selector.Slots"), CC.color(Hub.configuration.getConfiguration().getString("Inventory.Selector.Title")));
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (player.getItemInHand() == null || player.getItemInHand().getItemMeta() == null || player.getItemInHand().getItemMeta().getDisplayName() == null) return;
            if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(CC.color(Hub.configuration.getConfiguration().getString("Selector.Name")))) {
                for (final String id : Hub.configuration.getConfiguration().getConfigurationSection("Inventory.Selector.Items").getKeys(false)) {
                    int slot = Hub.configuration.getConfiguration().getInt("Inventory.Selector.Items." + id + "Slot");
                    ItemStack items = new ItemStack(Material.valueOf(Hub.configuration.getConfiguration().getString("Inventory.Selector.Items." + id + ".Item")));
                    ItemMeta itemsmeta = items.getItemMeta();
                    itemsmeta.setDisplayName(CC.color(Hub.configuration.getConfiguration().getString("Inventory.Selector.Items." + id + ".Name")));
                    ArrayList<String> lore = new ArrayList<>();
                    for (final String loremessage : Hub.configuration.getConfiguration().getStringList("Inventory.Selector.Items." + id + ".Lore")){
                        lore.add(CC.color(loremessage));
                    }
                    for (final String commands : Hub.configuration.getConfiguration().getStringList("Inventory.Selector.Items." + id + ".Commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("<player>", player.getName()));
                    }
                    itemsmeta.setLore(lore);
                    items.setItemMeta(itemsmeta);
                    inventory.setItem(slot, items);
                }
                player.openInventory(inventory);
            }
        }
    }

    @EventHandler
    public void onRightClick2(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Inventory inventory = Bukkit.createInventory(player, Hub.configuration.getConfiguration().getInt("Inventory.Hub.Slots"), CC.color(Hub.configuration.getConfiguration().getString("Inventory.Hub.Title")));
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (player.getItemInHand() == null || player.getItemInHand().getItemMeta() == null || player.getItemInHand().getItemMeta().getDisplayName() == null) return;
            if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(CC.color(Hub.configuration.getConfiguration().getString("Hub.Name")))) {
                for (final String id : Hub.configuration.getConfiguration().getConfigurationSection("Inventory.Hub.Items").getKeys(false)) {
                    int slot = Hub.configuration.getConfiguration().getInt("Inventory.Hub.Items." + id + "Slot");
                    ItemStack items = new ItemStack(Material.valueOf(Hub.configuration.getConfiguration().getString("Inventory.Hub.Items." + id + ".Item")));
                    ItemMeta itemsmeta = items.getItemMeta();
                    itemsmeta.setDisplayName(CC.color(Hub.configuration.getConfiguration().getString("Inventory.Hub.Items." + id + ".Name")));
                    ArrayList<String> lore = new ArrayList<>();
                    for (final String loremessage : Hub.configuration.getConfiguration().getStringList("Inventory.Hub.Items." + id + ".Lore")){
                        lore.add(CC.color(loremessage));
                    }
                    for (final String commands : Hub.configuration.getConfiguration().getStringList("Inventory.Hub.Items." + id + ".Commands")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("<player>", player.getName()));
                    }
                    itemsmeta.setLore(lore);
                    items.setItemMeta(itemsmeta);
                    inventory.setItem(slot, items);
                }
                player.openInventory(inventory);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory() == null || event.getClickedInventory().getTitle() == null) return;
        if (event.getClickedInventory().getTitle().equalsIgnoreCase(CC.color(Hub.configuration.getConfiguration().getString("Inventory.Selector.Title")))) {
            event.setCancelled(true);
        }

        for (final String id : Hub.configuration.getConfiguration().getConfigurationSection("Inventory.Selector.Items").getKeys(false)) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.color(Hub.configuration.getConfiguration().getString("Inventory.Selector.Items." + id + ".Name")))) {
                for (final String commands : Hub.configuration.getConfiguration().getStringList("Inventory.Selector.Items." + id + ".Commands")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("<player>", player.getName()));
                }
            }
        }
        if (event.getClickedInventory() == null || event.getClickedInventory().getTitle() == null) return;
        if (event.getClickedInventory().getTitle().equalsIgnoreCase(CC.color(Hub.configuration.getConfiguration().getString("Inventory.Hub.Title")))) {
            event.setCancelled(true);
        }

        for (final String id : Hub.configuration.getConfiguration().getConfigurationSection("Inventory.Hub.Items").getKeys(false)) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.color(Hub.configuration.getConfiguration().getString("Inventory.Hub.Items." + id + ".Name")))) {
                for (final String commands : Hub.configuration.getConfiguration().getStringList("Inventory.Hub.Items." + id + ".Commands")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("<player>", player.getName()));
                }
            }
        }
    }

    @EventHandler
    public void onChat2(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        event.setFormat(CC.color(Hub.lang.getConfiguration().getString("Chat-Format")).replace("<player>", player.getName()).replace("<rank>", CC.color(Hub.chat.getPrimaryGroup(player))).replace("<message>", event.getMessage()));
    }
}
