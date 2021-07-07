package xyz.zoradevelopment;

import cz.larkyy.llibrary.config.Config;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.zoradevelopment.commands.*;
import xyz.zoradevelopment.listeners.HubListener;
import xyz.zoradevelopment.providers.ScoreboardManager;
import xyz.zoradevelopment.providers.view.Scoreboard;

public final class Hub extends JavaPlugin {

    private static Hub instance;
    public static Config settings;
    public static Config configuration;
    public static Config links;
    public static Config lang;
    public static Chat chat;
    public static Config license;

    @Override
    public void onEnable() {
        instance = this;

        settings = new Config(Hub.getInstance(), "settings.yml");
        configuration = new Config(Hub.getInstance(), "configuration.yml");
        links = new Config(Hub.getInstance(), "links.yml");
        lang = new Config(Hub.getInstance(), "lang.yml");
        license = new Config(Hub.getInstance(), "license.yml");
        license.load();
        settings.load();
        configuration.load();
        links.load();
        lang.load();
        License();
        setupChat();
        new ScoreboardManager(this, new Scoreboard());
        hub();

    }

    public static Hub getInstance(){
        return instance;
    }

    @Override
    public void onDisable() {
    }

    public void hub(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new HubListener(), this);
        getCommand("zHub").setExecutor(new zHub());
        getCommand("Twitter").setExecutor(new Twitter());
        getCommand("Discord").setExecutor(new Discord());
        getCommand("Store").setExecutor(new Store());
        getCommand("Website").setExecutor(new Website());
        getCommand("Teamspeak").setExecutor(new Teamspeak());
    }

    private boolean setupChat() {
        RegisteredServiceProvider chatProvider = this.getServer().getServicesManager().getRegistration(Chat.class);
        if (chatProvider != null) {
            chat = (Chat) chatProvider.getProvider();
        }
        return chat != null;
    }

    public void License(){
        if(!new AdvancedLicense(getLicense().getString("License"), "https://dreamdevelopmentlicense.000webhostapp.com/verify.php", this).register()) return;
    }

    public FileConfiguration getLicense(){
        return license.getConfiguration();
    }
}
