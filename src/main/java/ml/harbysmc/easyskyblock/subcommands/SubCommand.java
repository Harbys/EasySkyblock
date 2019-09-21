package ml.harbysmc.easyskyblock.subcommands;

import org.bukkit.entity.Player;

abstract class SubCommand {
    abstract int getMinArgs();

    abstract String getHelp();

    abstract void handle(Player player, String[] args);
}
