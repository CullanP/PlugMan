package com.rylinaux.plugman;

import com.rylinaux.plugman.messaging.Messenger;

import java.util.List;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Plugin manager for Bukkit servers.
 *
 * @author rylinaux
 */
public class PlugMan extends JavaPlugin {

    /**
     * The instance of the plugin
     */
    private static PlugMan instance = null;

    /**
     * List of plugins to ignore, partially.
     */
    private List<String> ignoredPlugins = null;

    /**
     * The message manager
     */
    private Messenger messenger = null;

    @Override
    public void onEnable() {

        instance = this;

        messenger = new Messenger(this);

        this.getCommand("plugman").setExecutor(new PlugManCommands());
        this.getCommand("plugman").setTabCompleter(new PlugManTabCompleter());

        initConfig();

    }

    @Override
    public void onDisable() {
        instance = null;
        messenger = null;
        ignoredPlugins = null;
    }

    /**
     * Copy default config values
     */
    private void initConfig() {
        this.saveDefaultConfig();
        ignoredPlugins = this.getConfig().getStringList("ignored-plugins");
    }

    /**
     * Returns the instance of the plugin.
     *
     * @return the instance of the plugin
     */
    public static PlugMan getInstance() {
        return instance;
    }

    /**
     * Returns the list of ignored plugins.
     *
     * @return the ignored plugins
     */
    public List<String> getIgnoredPlugins() {
        return ignoredPlugins;
    }

    /**
     * Returns the message manager.
     *
     * @return the message manager
     */
    public Messenger getMessenger() {
        return messenger;
    }

}