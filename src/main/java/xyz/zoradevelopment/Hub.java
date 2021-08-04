package xyz.zoradevelopment;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import cz.larkyy.llibrary.config.Config;
import lombok.Getter;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.zoradevelopment.commands.*;
import xyz.zoradevelopment.developer.Join;
import xyz.zoradevelopment.gadets.doublejump.DoubleJump;
import xyz.zoradevelopment.gadets.lunchpads.LunchPad;
import xyz.zoradevelopment.listeners.HubListener;
import xyz.zoradevelopment.providers.ScoreboardManager;
import xyz.zoradevelopment.providers.view.Scoreboard;
import xyz.zoradevelopment.tablist.TablistManager;
import xyz.zoradevelopment.tablist.view.TabListListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class Hub extends JavaPlugin implements Listener, PluginMessageListener {

    @Getter
    private static Hub instance;
    public Map<String, Integer> playerCount;
    public static Config settings;
    public static Config configuration;
    public static Config links;
    public static Config lang;
    public static Chat chat;
    public static Config license;
    public static Config database;

    @Override
    public void onEnable() {
        instance = this;
        license = new Config(Hub.getInstance(), "license.yml");
        license.load();
        settings = new Config(Hub.getInstance(), "settings.yml");
        configuration = new Config(Hub.getInstance(), "configuration.yml");
        configuration.load();
        links = new Config(Hub.getInstance(), "links.yml");
        lang = new Config(Hub.getInstance(), "lang.yml");
        settings.load();
        configuration.load();
        links.load();
        lang.load();
        if(!new AdvancedLicense(Hub.license.getConfiguration().getString("License"), "https://dreamdevelopmentlicense.000webhostapp.com/verify.php", this).register()) return;
        setupChat();
        new ScoreboardManager(this, new Scoreboard());
        new TablistManager(this, new TabListListener(), TimeUnit.SECONDS.toMillis(1L));
        this.playerCount = new HashMap<>();
        hub();
        bungeecord();
        updateCount();
    }

    public static Hub getInstance(){
        return instance;
    }

    @Override
    public void onDisable() {
    }

    public void hub(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new LunchPad(), this);
        pm.registerEvents(new DoubleJump(), this);
        pm.registerEvents(new HubListener(), this);
        pm.registerEvents(new Join(), this);
        getCommand("ZoraHub").setExecutor(new ZoraHub());
        getCommand("Twitter").setExecutor(new Twitter());
        getCommand("Discord").setExecutor(new Discord());
        getCommand("Store").setExecutor(new Store());
        getCommand("Website").setExecutor(new Website());
        getCommand("Teamspeak").setExecutor(new Teamspeak());
        getCommand("List").setExecutor(new List());
    }

    private boolean setupChat() {
        RegisteredServiceProvider chatProvider = this.getServer().getServicesManager().getRegistration(Chat.class);
        if (chatProvider != null) {
            chat = (Chat) chatProvider.getProvider();
        }
        return chat != null;
    }

    private void bungeecord() {
        this.getServer().getMessenger().registerOutgoingPluginChannel((Plugin)this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel((Plugin)this, "BungeeCord", this);
    }

    private final void updateCount() {
        new BukkitRunnable(){

            public void run() {
                if (Bukkit.getServer().getOnlinePlayers().size() > 0) {
                    Hub.this.getCount((Player) Bukkit.getServer().getOnlinePlayers().toArray()[0], null);
                }
            }
        }.runTaskTimerAsynchronously((Plugin)this, 20L, 20L);
    }


    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        try {
            if (message.length == 0) {
                return;
            }
            ByteArrayDataInput in = ByteStreams.newDataInput((byte[])message);
            String subchannel = in.readUTF();
            if (subchannel.equals("PlayerCount")) {
                String server = in.readUTF();
                int playerCount = in.readInt();
                this.playerCount.put(server, playerCount);
            }
        }
        catch (Exception exception) {

        }
    }

    public void getCount(Player player, String server) {
        if (server == null) {
            server = "ALL";
        }
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF(server);
        player.sendPluginMessage((Plugin)this, "BungeeCord", out.toByteArray());
    }

    public int getOnlineCount(String server) {
        if (server == null) {
            server = "ALL";
            int online = 0;
            for (int next : this.playerCount.values()) {
                if (next <= 0) continue;
                online += next;
            }
            return online;
        }
        this.playerCount.putIfAbsent(server, -1);
        return this.playerCount.get(server);
    }

}
