package xyz.zoradevelopment.listeners;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import org.spigotmc.event.entity.EntityDismountEvent;
import xyz.zoradevelopment.Hub;
import xyz.zoradevelopment.managers.HubManager;
import xyz.zoradevelopment.utils.CC;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

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
        Player player = event.getPlayer();
        if (!enabled) {
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        final boolean enabled = Hub.settings.getConfiguration().getBoolean("break-blocks");
        Player player = event.getPlayer();
        if (!enabled) {
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onTake(PlayerPickupItemEvent event) {
        final boolean enabled = Hub.settings.getConfiguration().getBoolean("pickup-items");
        if (!enabled) {
            event.setCancelled(true);
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
        final boolean fireworkenabled = Hub.settings.getConfiguration().getBoolean("Fireworks");
        if (fireworkenabled) {
            Firework firework = player.getWorld().spawn(player.getLocation(), Firework.class);
            FireworkMeta meta = firework.getFireworkMeta();
            meta.addEffect(FireworkEffect.builder().withColor(Color.AQUA).withColor(Color.GREEN).withColor(Color.RED).withColor(Color.YELLOW).with(FireworkEffect.Type.BALL_LARGE).withFlicker().build());
            meta.setPower(1);
            firework.setFireworkMeta(meta);
        }
        final boolean spawnenabled = Hub.configuration.getConfiguration().getBoolean("Spawn.Enabled");
        int x = Hub.configuration.getConfiguration().getInt("Spawn.x");
        int y = Hub.configuration.getConfiguration().getInt("Spawn.y");
        int z = Hub.configuration.getConfiguration().getInt("Spawn.z");
        if (spawnenabled) {
            player.teleport(new Location(Bukkit.getWorld("World"),x,y,z));
        }
        int online = Bukkit.getOnlinePlayers().size();
        String players = String.valueOf(online);
        final boolean enabled = Hub.settings.getConfiguration().getBoolean("join-message");
        if (enabled) {
            for (final String joinmessage : Hub.lang.getConfiguration().getStringList("Join.Message")) {
                player.sendMessage(CC.color(joinmessage).replace("<player>", player.getName()).replace("<online_players>", players).replace("<rank>", Hub.chat.getPrimaryGroup(player)));
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
    public void on(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack itemInHand = player.getItemInHand();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getGameMode() == GameMode.CREATIVE) {
                return;
            }
            if (itemInHand.getType() == Material.ENDER_PEARL) {
                player.setVelocity(player.getLocation().getDirection().normalize().multiply(4));
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 10F);
                player.updateInventory();
                e.setCancelled(true);
            }
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
                    int slot = Hub.configuration.getConfiguration().getInt("Inventory.Selector.Items." + id + ".Slot");
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
                    int slot = Hub.configuration.getConfiguration().getInt("Inventory.Hub.Items." + id + ".Slot");
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
            if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null || event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.color(Hub.configuration.getConfiguration().getString("Inventory.Selector.Items." + id + ".Name")))) {
                for (final String commands : Hub.configuration.getConfiguration().getStringList("Inventory.Selector.Items." + id + ".Commands")) {
                    player.performCommand(commands);
                }
            }
        }
        if (event.getClickedInventory() == null || event.getClickedInventory().getTitle() == null) return;
        if (event.getClickedInventory().getTitle().equalsIgnoreCase(CC.color(Hub.configuration.getConfiguration().getString("Inventory.Hub.Title")))) {
            event.setCancelled(true);
        }

        for (final String id : Hub.configuration.getConfiguration().getConfigurationSection("Inventory.Hub.Items").getKeys(false)) {
            if (event.getCurrentItem() == null || event.getCurrentItem().getItemMeta() == null || event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.color(Hub.configuration.getConfiguration().getString("Inventory.Hub.Items." + id + ".Name")))) {
                for (final String commands : Hub.configuration.getConfiguration().getStringList("Inventory.Hub.Items." + id + ".Commands")) {
                    player.performCommand(commands);
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
