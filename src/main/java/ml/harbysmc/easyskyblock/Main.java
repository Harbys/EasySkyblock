package ml.harbysmc.easyskyblock;

import ml.harbysmc.easyskyblock.commands.*;
import ml.harbysmc.easyskyblock.models.IslandConfig;
import ml.harbysmc.easyskyblock.subcommands.CommandHandler;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import ml.harbysmc.easyskyblock.listeners.BlockBreakListener;
import ml.harbysmc.easyskyblock.listeners.BlockPlaceListener;


public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigurationSerialization.registerClass(IslandConfig.class);
        reloadConfig();

        addCommand("is", new CommandHandler(this));
        addCommand("ulepsz", new Ulepsz());
        addCommand("stonemaker", new Stonemaker());

        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
    }

    private void addCommand(String commandName, CommandExecutor executor) {
        PluginCommand command = getCommand(commandName);

        if (command == null) {
            getLogger().severe("Something is fucked up. Check plugin.yml.");
            setEnabled(false);
            return;
        }

        command.setExecutor(executor);
    }
}
