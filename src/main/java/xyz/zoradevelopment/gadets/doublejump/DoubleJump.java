package xyz.zoradevelopment.gadets.doublejump;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.entity.*;
import org.bukkit.*;
import org.bukkit.plugin.*;
import xyz.zoradevelopment.Hub;

public class DoubleJump implements Listener
{
    @EventHandler
    public void onPlayerToggleFlight(final PlayerToggleFlightEvent event) {
        final boolean enabled = Hub.settings.getConfiguration().getBoolean("Doublejump");
        final Player player = event.getPlayer();
        if (enabled) {
            player.playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
            if (player.getGameMode() == GameMode.CREATIVE) {
                return;
            }
            event.setCancelled(true);
            player.setAllowFlight(false);
            player.setFlying(false);
            player.playSound(player.getLocation(), Sound.EXPLODE, 1, 1);
            player.setVelocity(player.getLocation().getDirection().multiply(1.5).setY(1));
        }
    }
    
    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {
        final boolean enabled = Hub.settings.getConfiguration().getBoolean("Doublejump");
        final Player player = event.getPlayer();
        if (enabled) {
            if (player.getGameMode() != GameMode.CREATIVE && player.getLocation().subtract(0.0, 1.0, 0.0).getBlock().getType() != Material.AIR && !player.isFlying()) {
                player.setAllowFlight(true);
            }
        }
    }
    
    @EventHandler
    public void onFallDamage(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void move(final PlayerMoveEvent e) {
        final boolean enabled = Hub.settings.getConfiguration().getBoolean("Doublejump");
        final Player f = e.getPlayer();
        if (enabled) {
            if (e.getTo().getY() < 2.0) {
                Hub.getInstance().getServer().getScheduler().scheduleSyncDelayedTask((Plugin) Hub.getInstance(), (Runnable) new Runnable() {
                    @Override
                    public void run() {
                        final double y = f.getLocation().getY() - 2.0;
                        final Location l = new Location(f.getLocation().getWorld(), f.getLocation().getX(), y, f.getLocation().getZ(), f.getLocation().getYaw(), f.getLocation().getPitch());
                        f.getWorld().playEffect(l, Effect.ENDER_SIGNAL, 50, 30);
                    }
                }, 10L);
            }
        }
    }
}
