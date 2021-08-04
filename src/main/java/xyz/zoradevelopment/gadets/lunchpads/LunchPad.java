package xyz.zoradevelopment.gadets.lunchpads;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class LunchPad implements Listener {

    private ArrayList<Player> jumpers = new ArrayList<Player>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.STONE_PLATE) {
            e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(3));
            e.getPlayer().setVelocity(new Vector(e.getPlayer().getVelocity().getX(), 1.0D, e.getPlayer().getVelocity().getZ()));
            jumpers.add(e.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerMove2(PlayerMoveEvent e) {
        if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.GOLD_PLATE) {
            e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(6));
            e.getPlayer().setVelocity(new Vector(e.getPlayer().getVelocity().getX(), 2.0D, e.getPlayer().getVelocity().getZ()));
            jumpers.add(e.getPlayer());
        }
    }
}