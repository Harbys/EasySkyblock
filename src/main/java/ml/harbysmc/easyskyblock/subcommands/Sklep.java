package ml.harbysmc.easyskyblock.subcommands;

import ml.harbysmc.easyskyblock.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


class Sklep extends SubCommand {

    @Override
    void handle(Player player, String[] args) {
        if (!player.hasPermission(Permissions.SHOP)) {
            player.sendMessage("§cYou can't do this right now!");
            return;
        }

        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "warp shop " + player.getName());
    }

    @Override
    int getMinArgs() {
        return 0;
    }

    @Override
    String getHelp() {
        return null;
    }

}
